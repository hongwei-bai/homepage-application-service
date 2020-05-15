package com.hongwei.model.covid19

data class AusDataByStatePerDay(
        var date: String,
        var dateFrom: String,
        var timeFrom: String,
        var NSW: Long,
        var VIC: Long,
        var QLD: Long,
        var SA: Long,
        var WA: Long,
        var TAS: Long,
        var NT: Long,
        var ACT: Long,
        var Total_Cases: Long
)