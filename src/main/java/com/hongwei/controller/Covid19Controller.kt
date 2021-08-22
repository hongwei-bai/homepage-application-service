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

	@RequestMapping(path = ["/test.do"])
	@ResponseBody
	fun test(): ResponseEntity<*> {
		return ResponseEntity.ok(auGovCovidService.test())
	}

	@PutMapping(path = ["/auPostcodes.do"])
	@ResponseBody
	fun generateAustralianPostcodes(): ResponseEntity<*> {
		return ResponseEntity.ok(auGovCovidService.initializeSuburb())
	}

	@RequestMapping(path = ["/auBrief.do"])
	@ResponseBody
	fun getAuCovidBrief(dataVersion: Long, days: Long, top: Int, followedSuburbs: String): ResponseEntity<*> {
		return ResponseEntity.ok(auGovCovidService.getAuCovidBriefData(dataVersion, days, top,
			followedSuburbs.split(",").toList().mapNotNull { it.toLongOrNull() }
		))
	}
}