package com.hongwei.model.covid19

import com.hongwei.model.covid19mobile.MobileCovidAu
import com.hongwei.model.jpa.au.AuSuburbRepository
import com.hongwei.model.jpa.au.CovidAuEntity
import com.hongwei.util.DateTimeParseUtil
import org.apache.log4j.LogManager
import org.apache.log4j.Logger
import java.util.*

object CovidAuMapper {
	private const val MILLIS_PER_DAY = 24 * 60 * 60 * 1000

	private val logger: Logger = LogManager.getLogger(CovidAuMapper::class.java)

	fun map(postcodeRepo: AuSuburbRepository, rawMobileData: MobileCovidAu): CovidAuEntity =
		CovidAuEntity(
			dataVersion = rawMobileData.dataVersion,
			dataByDay = rawMobileData.dataByDay.map { mobileDataByDay ->
				val today = Calendar.getInstance().apply {
					set(Calendar.MILLISECOND, 0);
					set(Calendar.SECOND, 0);
					set(Calendar.MINUTE, 0);
					set(Calendar.HOUR, 0);
				}.time
				CovidAuDay(
					dayDiff = (today.time - mobileDataByDay.date) / MILLIS_PER_DAY,
					dateUnixTimeStamp = mobileDataByDay.date,
					dateDisplay = DateTimeParseUtil.toDisplay(Date(mobileDataByDay.date)),
					caseByState = mobileDataByDay.caseByState.map { mobileDataByState ->
						CovidAuCaseByState(
							stateCode = mobileDataByState.stateCode,
							stateName = mobileDataByState.stateName,
							cases = mobileDataByState.cases
						)
					},
					caseExcludeFromStates = mobileDataByDay.caseExcludeFromStates,
					caseTotal = mobileDataByDay.caseTotal,
					caseByPostcode = mobileDataByDay.caseByPostcode.mapNotNull { mobileDataByPostcode ->
						val suburbInfo = postcodeRepo.findSuburb(mobileDataByPostcode.postcode)
						suburbInfo?.let {
							CovidAuCaseByPostcode(
								postcode = mobileDataByPostcode.postcode,
								suburbs = suburbInfo.suburbs,
								suburbBrief = CovidAuMapper.getSuburbBrief(suburbInfo.suburbs) ?: "",
								latitude = suburbInfo.latitude,
								longitude = suburbInfo.longitude,
								accuracy = suburbInfo.accuracy,
								state = suburbInfo.stateCode.toUpperCase(),
								cases = mobileDataByPostcode.cases
							)
						}
					}
				)
			}
		)

	fun getSuburbBrief(suburbs: List<String>): String? = when (suburbs.size) {
		0 -> null
		1 -> suburbs.first()
		else -> {
			var result: String? = null
			// Priority No.1
			suburbs.forEachIndexed { i, suburbOut ->
				suburbs.forEachIndexed { j, suburbInner ->
					if (i != j && suburbOut.contains(suburbInner)) {
						result = suburbInner
					}
				}
			}

			// Priority No.2
			if (result == null) {
				val wordRepeatMap = hashMapOf<String, Int>()
				suburbs.forEach {
					val words = it.split(" ")
					words.forEach { word ->
						if (!SuburbMeaninglessWords.contains(word)) {
							wordRepeatMap[word] = 1 + (wordRepeatMap[word] ?: 0)
						}
					}
				}
				val list = wordRepeatMap.toList().sortedByDescending { it.second }
				if (list.first().second >= 2) {
					result = list.first().first
				}
			}

			result ?: suburbs.first()
		}
	}

	private val SuburbMeaninglessWords = listOf(
		"West", "East", "North", "South", "Park", "Hill", "Hills", "Point", "River", "Estate", "Old", "Mountain", "Valley", "Ridge", "Creek", "Island", "Junction", "Vale", "Bay",
		"Harbour", "Farm", "Cove", "Beach", "Centre", "Shore", "Hospital", "Head", "Airport", "Grove", "Heights", "Plateau", "Caves", "Town", "Forest", "Flat", "Walls", "Crossing", "DC"
	)
}