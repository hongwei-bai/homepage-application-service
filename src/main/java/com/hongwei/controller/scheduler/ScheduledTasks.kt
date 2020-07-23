package com.hongwei.controller.scheduler

import com.hongwei.controller.Covid19Controller
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.scheduling.annotation.Scheduled
import org.springframework.stereotype.Component


@Component
class ScheduledTasks {
    @Autowired
    private val covid19Controller: Covid19Controller? = null

    // 30 mins : 30 min x 60 s x 1000 ms = 1,800,000, For copy:1800000
    @Scheduled(fixedRate = 1800000)
    fun reportCurrentTime() {
        covid19Controller?.queryAuDataByStateFromTimer()
    }
}