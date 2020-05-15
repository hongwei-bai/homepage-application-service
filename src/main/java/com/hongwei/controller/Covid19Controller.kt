package com.hongwei.controller

import com.google.gson.Gson
import com.hongwei.model.arcgis.covid19.Attributes
import com.hongwei.model.arcgis.covid19.BeanByAuState
import com.hongwei.model.arcgis.covid19.Features
import com.hongwei.model.covid19.AusDataByState
import com.hongwei.model.covid19.AusDataByStatePerDay
import org.springframework.web.bind.annotation.CrossOrigin
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.ResponseBody
import org.springframework.web.bind.annotation.RestController
import org.springframework.web.client.RestTemplate
import java.text.SimpleDateFormat
import java.util.*

@RestController
@RequestMapping("/covid19")
@CrossOrigin
class Covid19Controller {
    @RequestMapping(path = ["/querybystate.do"])
    @ResponseBody
    fun getAuDataByState(days: Int): String {
        val uri = "https://services1.arcgis.com/vHnIGBHHqDR6y0CR/arcgis/rest/services/COVID19_Time_Series/FeatureServer/0/query?where=1=1&outFields=*&outSR=4326&f=json"
        val restTemplate = RestTemplate()
        val json = restTemplate.getForObject(uri, String::class.java)
        val data = Gson().fromJson(json, BeanByAuState::class.java)
        val features = data.features
        val outData = parseFeatures(features, days)
        return Gson().toJson(outData)
    }

    private fun parseFeatures(features: Array<Features>, daysIn: Int): AusDataByState {
        val days = Math.min(daysIn, features.size - 1)
        val lastDay = features.size - 1
        val dayFrom = lastDay - days
        var previous: Attributes? = null
        val date = AusDataByState(Array<AusDataByStatePerDay?>(lastDay - dayFrom + 1) { null })
        for (i in dayFrom..lastDay) {
            val current = features[i].attributes
            if (previous != null) {
                date.ausDataByStatePerDays[days - (i - dayFrom)] = parseDateByDay(previous, current)
            }
            previous = current
        }
        return date
    }

    private fun parseDateByDay(previous: Attributes, current: Attributes): AusDataByStatePerDay {
        val dateFormat = SimpleDateFormat("yyyy-MM-dd")
        val timeFormat = SimpleDateFormat("HH:mm z")
        dateFormat.timeZone = TimeZone.getTimeZone("Australia/NSW")
        timeFormat.timeZone = TimeZone.getTimeZone("Australia/NSW")
        val date = Date(current.Date)
        val date0 = Date(previous.Date)
        return AusDataByStatePerDay(
                date = dateFormat.format(date),
                dateFrom = dateFormat.format(date0),
                timeFrom = timeFormat.format(date),
                NSW = calcLongDiff(current.NSW, previous.NSW),
                VIC = calcLongDiff(current.VIC, previous.VIC),
                QLD = calcLongDiff(current.QLD, previous.QLD),
                SA = calcLongDiff(current.SA, previous.SA),
                WA = calcLongDiff(current.WA, previous.WA),
                TAS = calcLongDiff(current.TAS, previous.TAS),
                NT = calcLongDiff(current.NT, previous.NT),
                ACT = calcLongDiff(current.ACT, previous.ACT),
                Total_Cases = current.Total_Cases
        )
    }

    private fun calcLongDiff(current: Long, previous: Long?): Long {
        return if (current != null && previous != null) {
            current - previous
        } else if (current != null && previous == null) {
            current
        } else {
            0
        }
    }
}