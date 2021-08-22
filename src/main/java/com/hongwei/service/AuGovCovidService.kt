package com.hongwei.service

import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.hongwei.constants.*
import com.hongwei.model.au.AuPostcodeSource
import com.hongwei.model.covid19.*
import com.hongwei.model.covid19mobile.MobileCovidAu
import com.hongwei.model.jpa.au.AuSuburbEntity
import com.hongwei.model.jpa.au.AuSuburbRepository
import com.hongwei.model.jpa.au.CovidAuRepository
import com.hongwei.util.LocalJsonReaderUtil
import org.apache.log4j.LogManager
import org.apache.log4j.Logger
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.*
import org.springframework.stereotype.Service
import org.springframework.web.client.RestTemplate


@Service
class AuGovCovidService {
	private val logger: Logger = LogManager.getLogger(AuGovCovidService::class.java)

	@Autowired
	private lateinit var securityConfigurations: SecurityConfigurations

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

	fun getAuCovidBriefData(dataVersionApi: Long, inDays: Long, top: Int, followedPostcodes: List<Long>): CovidAuSuburbBreif {
		val entityDb = covidAuRepository.findRecentRecords().firstOrNull()
		val dbDataVersion = entityDb?.dataVersion ?: 0L
		val rawData = fetchMobileRawData(inDays, dbDataVersion)
		val data = if (rawData != null) {
			val entity = CovidAuMapper.map(auSuburbRepository, rawData)
			covidAuRepository.save(entity)
			entity
		} else {
			entityDb
		}
		data?.run {
			if (dataVersionApi < dataVersion) {
				return CovidAuSuburbBreif(
					dataVersion = dataVersion,
					dataByDay = dataByDay.map { generalDataByDay ->
						CovidAuDayBrief(
							dayDiff = generalDataByDay.dayDiff,
							dateDisplay = generalDataByDay.dateDisplay,
							caseByState = generalDataByDay.caseByState,
							caseExcludeFromStates = generalDataByDay.caseExcludeFromStates,
							caseTotal = generalDataByDay.caseTotal,
							caseByPostcodeTops = generalDataByDay.caseByPostcode
								.sortedByDescending { it.cases }
								.filterIndexed { index, _ -> index < top }
								.map {
									CovidAuCaseByPostcodeBrief(
										postcode = it.postcode,
										suburbBrief = it.suburbBrief,
										state = it.state,
										cases = it.cases
									)
								},
							caseByPostcodeFollowed = generalDataByDay.caseByPostcode
								.filter { followedPostcodes.contains(it.postcode) }
								.sortedByDescending { it.cases }
								.map {
									CovidAuCaseByPostcodeBrief(
										postcode = it.postcode,
										suburbBrief = it.suburbBrief,
										state = it.state,
										cases = it.cases
									)
								}
						)
					}
				)
			} else throw ResetContent
		} ?: throw  InternalServerError
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

	private fun fetchMobileRawData(days: Long, dataVersion: Long): MobileCovidAu? {
		try {
			val uri = "${securityConfigurations.covidDomain}/covid/au/raw.do?days=$days&dataVersion=$dataVersion"
			val headers = HttpHeaders()
			headers.add(securityConfigurations.authorizationHeader, "${securityConfigurations.authorizationBearer} ${securityConfigurations.covidApikey}")
			val requestEntity: HttpEntity<Any> = HttpEntity<Any>(null, headers)
			val response: ResponseEntity<MobileCovidAu> = RestTemplate().exchange(uri, HttpMethod.GET, requestEntity, MobileCovidAu::class.java)
			if (response.statusCode.is2xxSuccessful) {
				if (response.statusCode == HttpStatus.RESET_CONTENT) {
					return null
				}
				return response.body
			}
		} catch (e: Unauthorized) {
			throw e
		} catch (e: Exception) {
			if (e.message?.contains("JWT expired") == true) {
				throw AppTokenExpiredException
			}
			e.printStackTrace()
			throw InternalServerError
		}
		return null
	}
}