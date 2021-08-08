package com.hongwei.model.covid19

data class CovidAuSuburbBreif(
	val dataVersion: Long,
	val dataByDay: List<CovidAuDayBrief>
)

data class CovidAuDayBrief(
	var dayDiff: Long,
	var dateDisplay: String,
	var caseByState: List<CovidAuCaseByState> = emptyList(),
	var caseExcludeFromStates: Int = 0,
	var caseTotal: Int = 0,
	var caseByPostcode: List<CovidAuCaseByPostcodeBrief> = emptyList()
)

data class CovidAuCaseByPostcodeBrief(
	val postcode: Long,
	val suburbBrief: String,
	val state: String,
	val cases: Int
)