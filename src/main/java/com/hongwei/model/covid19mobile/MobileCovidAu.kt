package com.hongwei.model.covid19mobile


data class MobileCovidAu(
	val dataVersion: Long,
	val lastUpdate: String,
	val recordsCount: Int,
	val lastRecordDate: String,
	var dataByDay: List<MobileCovidAuDay>
)

data class MobileCovidAuDay(
	var date: Long,
	var caseByState: List<MobileCovidAuCaseByState> = emptyList(),
	var caseExcludeFromStates: Int = 0,
	var caseTotal: Int = 0,
	var caseByPostcode: List<MobileCovidAuCaseByPostcode> = emptyList()
)

data class MobileCovidAuCaseByState(
	val stateCode: String,
	val stateName: String,
	val cases: Int
)

data class MobileCovidAuCaseByPostcode(
	val postcode: Long,
	val cases: Int
)