package com.hongwei.service

import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.hongwei.constants.NoContent
import com.hongwei.constants.ResetContent
import com.hongwei.model.au.AuPostcodeSource
import com.hongwei.model.covid19.CovidAuMapper
import com.hongwei.model.covid19.auGov.AuGovCovidMapper
import com.hongwei.model.covid19.auGov.AuGovCovidSource
import com.hongwei.model.jpa.au.*
import com.hongwei.util.CsvUtil
import com.hongwei.util.LocalJsonReaderUtil
import com.hongwei.util.curl.CUrlWrapper
import org.apache.log4j.LogManager
import org.apache.log4j.Logger
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service


@Service
class AuGovCovidService {
	private val logger: Logger = LogManager.getLogger(AuGovCovidService::class.java)

	companion object {
		private const val AU_GOV_COVID_DATA_URL = "https://data.nsw.gov.au/data/dataset/nsw-covid-19-cases-by-location-and-likely-source-of-infection/resource/2776dbb8-f807-4fb2-b1ed-184a6fc2c8aa"

		private const val LOCATE_STRING_OPEN = "\"DCTERMS.Identifier\""
	}

	@Autowired
	private lateinit var auSuburbRepository: AuSuburbRepository

	@Autowired
	private lateinit var covidAuRepository: CovidAuRepository

	fun test(): Any? {
		val testSuburbs = listOf(
			"Smithfield West",
			"Smithfield",
			"Woodpark",
			"Wetherill Park"
		)
		val testSuburbs2 = listOf(
			"Lalor Park",
			"Seven Hills West",
			"Seven Hills",
			"Kings Langley"
		)

		return CovidAuMapper.getSuburbBrief(testSuburbs)
	}

	fun getAuCovidData(dataVersion: Long, inDays: Long?): CovidAuEntity {
		val entityDb = covidAuRepository.findRecentRecord()
		entityDb?.let {
			return if (dataVersion < entityDb.dataVersion) {
				entityDb
					.apply {
						dataByDay.filter { it.dayDiff <= inDays ?: 2 }
					}
			} else throw ResetContent
		} ?: throw NoContent
	}

	fun parseCsv(): CovidAuEntity? {
		val lines = CsvUtil.readCSVFromUrl(getCSVUrl())
		val sourceList = mutableListOf<AuGovCovidSource>()
		lines.forEach {
			val data = it.split(",")
			when (data.size) {
				7 -> AuGovCovidSource(
					date = data.first(),
					postcode = data[1].toLongOrNull() ?: 0L,
					likelySourceOfInfection = data[2],
					lhd2010Code = data[3],
					lhd2010Name = data[4],
					lgaCode19 = data[5].toLongOrNull() ?: 0L,
					lgaName19 = data[6]
				)
				else -> {
					logger.debug("unrecognized record: $data")
					null
				}
			}?.let { record ->
				sourceList.add(record)
			}
		}
		val notifications = sourceList.mapNotNull { AuGovCovidMapper.map(auSuburbRepository, it) }
		val entityDb = covidAuRepository.findRecentRecord()
		val entity = CovidAuMapper.map(auSuburbRepository, notifications)
		if (entity != null && entityDb != entity) {
			if (covidAuRepository.findAll().isNotEmpty()) {
				covidAuRepository.deleteAll()
			}
			covidAuRepository.save(entity)
			return entity
		}
		return entityDb
	}

	fun initializeSuburb(): List<AuSuburbEntity>? {
		val suburbs = auSuburbRepository.findAllSuburbs()
		if (suburbs?.isNotEmpty() == true) {
			return suburbs
		}

		val jsonString = LocalJsonReaderUtil.readJsonFileInResource("data/au_postcodes.json")
		val auPostcodeSource = Gson().fromJson<List<AuPostcodeSource>>(jsonString,
			object : TypeToken<List<AuPostcodeSource>>() {}.type)
		val hashMap = hashMapOf<Long, AuSuburbEntity>()
		auPostcodeSource.forEach {
			if (hashMap.containsKey(it.postcode)) {
				hashMap[it.postcode]?.suburbs?.add(it.place_name)
			} else {
				hashMap[it.postcode] = AuSuburbEntity(
					postcode = it.postcode,
					suburbs = mutableListOf<String>().apply { add(it.place_name) },
					state = it.state_name,
					stateCode = it.state_code,
					latitude = it.latitude,
					longitude = it.longitude,
					accuracy = it.accuracy
				)
			}
		}
		val list = hashMap.map { it.value }
		auSuburbRepository.saveAll(list)
		return list
	}

	private fun getCSVUrl(): String {
		CUrlWrapper.curl(AU_GOV_COVID_DATA_URL)?.toString()?.run {
			val index0 = indexOf(LOCATE_STRING_OPEN)
			val mid2 = substring(index0 + LOCATE_STRING_OPEN.length)
			val index1 = mid2.indexOf("\">")
			val mid3 = mid2.substring(0, index1)
			val mid4 = mid3.replace("content=\"", "").trim()
			return mid4
		}
		return ""
	}
}