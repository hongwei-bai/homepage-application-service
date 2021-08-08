package com.hongwei.model.covid19

import com.hongwei.model.covid19.CovidAuMapper.getSuburbBrief
import com.hongwei.model.jpa.au.AuSuburbRepository
import com.hongwei.model.jpa.au.CovidAuBriefEntity
import com.hongwei.util.DateTimeParseUtil.toDisplay
import com.hongwei.util.TimeStampUtil
import org.apache.log4j.LogManager
import org.apache.log4j.Logger

object CovidAuSuburbBriefMapper {
	private val logger: Logger = LogManager.getLogger(CovidAuSuburbBriefMapper::class.java)

	fun map(postcodeRepo: AuSuburbRepository, mid: List<AuGovCovidNotification>): CovidAuBriefEntity =
		CovidAuBriefEntity(
			dataVersion = TimeStampUtil.getTimeVersionWithHour(),
			dataByDayBrief = mid.groupBy { it.dayDiff }.mapNotNull { dayDiffToNotificationMap ->
				val notificationsByDay = dayDiffToNotificationMap.value
				dayDiffToNotificationMap.value.firstOrNull()?.let { firstNotification ->
					CovidAuDayBrief(
						dayDiff = firstNotification.dayDiff,
						dateDisplay = toDisplay(firstNotification.date),
						caseByState = notificationsByDay.groupBy { it.state }.mapNotNull { stateToNotificationsMap ->
							stateToNotificationsMap.key?.let { stateCode ->
								CovidAuCaseByState(
									stateCode = stateCode.toUpperCase(),
									stateName = AuState.valueOf(stateCode.toLowerCase()).fullName,
									cases = stateToNotificationsMap.value.size
								)
							}
						}.sortedByDescending { it.cases },
						caseExcludeFromStates = notificationsByDay.filter { it.state == null }.size,
						caseTotal = notificationsByDay.size,
						caseByPostcode = notificationsByDay.groupBy { it.postcode }.mapNotNull { postcodeToNotificationsMap ->
							postcodeToNotificationsMap.key?.let { postcode ->
								val postcodeData = postcodeRepo.findSuburb(postcode)
								val suburbs = postcodeData?.suburbs ?: emptyList<String>()
								if (postcodeData != null) {
									CovidAuCaseByPostcodeBrief(
										postcode = postcode,
										suburbBrief = getSuburbBrief(suburbs) ?: "",
										state = postcodeData.stateCode.toUpperCase(),
										cases = postcodeToNotificationsMap.value.size
									)
								} else null
							}
						}.sortedByDescending { it.cases }
					)
				}
			}.sortedBy { it.dayDiff }
		)
}