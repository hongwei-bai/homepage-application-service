package com.hongwei.service.scheduler

import com.hongwei.service.AuGovCovidService
import com.hongwei.util.TimeStampUtil.SYDNEY
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.scheduling.annotation.Scheduled
import org.springframework.stereotype.Component
import java.util.*


@Component
class ScheduledTasks {
	@Autowired
	private lateinit var auGovCovidService: AuGovCovidService

	private var onStart = true

	// 60 mins : 60 min x 60 s x 1000 ms = 1,800,000, For copy:3600000
	@Scheduled(fixedRate = 3600000)
	fun reportCurrentTime() {
		val sydTime = Calendar.getInstance(TimeZone.getTimeZone(SYDNEY))
		val hour = sydTime.get(Calendar.HOUR_OF_DAY)

		Thread {
			if (onStart) {
				onStart = false
				auGovCovidService.initializeSuburb()
			}

			if (AustralianCovidUpdateHours.contains(hour)) {
				// No-Op
			}
		}.start()
	}

	companion object {
		val AustralianCovidUpdateHours = listOf(6, 14, 16)
	}
}