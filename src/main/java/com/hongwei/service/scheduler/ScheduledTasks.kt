package com.hongwei.service.scheduler

import com.hongwei.service.AuGovCovidService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.scheduling.annotation.Scheduled
import org.springframework.stereotype.Component
import java.util.*


@Component
class ScheduledTasks {
    @Autowired
    private lateinit var auGovCovidService: AuGovCovidService

    // 60 mins : 60 min x 60 s x 1000 ms = 1,800,000, For copy:3600000
    @Scheduled(fixedRate = 3600000)
    fun reportCurrentTime() {
        val sydTime = Calendar.getInstance(TimeZone.getTimeZone(SYDNEY))
        val hour = sydTime.get(Calendar.HOUR_OF_DAY)

        Thread {
            if (AustralianCovidUpdateHours.contains(hour)) {
                auGovCovidService.initializeSuburb()
                auGovCovidService.parseCsv()
            }
        }.start()
    }

    companion object {
        val AustralianCovidUpdateHours = listOf(6, 16)

        const val UTC = "UTC"
        const val SYDNEY = "Australia/Sydney"
    }
}