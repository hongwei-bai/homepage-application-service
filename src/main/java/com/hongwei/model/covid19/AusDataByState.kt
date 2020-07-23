package com.hongwei.model.covid19

data class AusDataByState(
        var isNewData: Boolean = false,
        var ausDataByStatePerDays: Array<AusDataByStatePerDay?>
)