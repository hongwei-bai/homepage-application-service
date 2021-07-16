package com.hongwei.model.covid19

data class AusDataByStatePerDay(
        var date: String,
        var dateFrom: String = "",
        var timeFrom: String = "",
        var NSW: Long = 0,
        var VIC: Long = 0,
        var QLD: Long = 0,
        var SA: Long = 0,
        var WA: Long = 0,
        var TAS: Long = 0,
        var NT: Long = 0,
        var ACT: Long = 0,
        var Total_Cases: Long = 0,
        var caseByPostcode: List<Triple<Long, String, Long>> = emptyList()
)