package com.hongwei.controller

import com.hongwei.service.AuGovCovidService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/covid19")
class Covid19Controller {
	@Autowired
	private lateinit var auGovCovidService: AuGovCovidService

	@PutMapping(path = ["/auPostcodes.do"])
	@ResponseBody
	fun generateAustralianPostcodes(): ResponseEntity<*> {
		return ResponseEntity.ok(auGovCovidService.initializeSuburb())
	}

	@PutMapping(path = ["/auGovData.do"])
	@ResponseBody
	fun generateAuCovidData(): ResponseEntity<*> {
		return ResponseEntity.ok(auGovCovidService.parseCsv())
	}

	@RequestMapping(path = ["/auGovDataTest.do"])
	@ResponseBody
	fun generateAuCovidDataTest(): ResponseEntity<*> {
		return ResponseEntity.ok(auGovCovidService.test())
	}

	@RequestMapping(path = ["/au.do"])
	@ResponseBody
	fun getAuCovid(dataVersion: Long, days: Long? = null): ResponseEntity<*> {
		return ResponseEntity.ok(auGovCovidService.getAuCovidData(dataVersion, days))
	}

	@RequestMapping(path = ["/auBrief.do"])
	@ResponseBody
	fun getAuCovidBrief(dataVersion: Long, days: Long, top: Int, followedSuburbs: String): ResponseEntity<*> {
		return ResponseEntity.ok(auGovCovidService.getAuCovidBriefData(dataVersion, days, top,
			followedSuburbs.split(",").toList().mapNotNull { it.toLongOrNull() }
		))
	}
}