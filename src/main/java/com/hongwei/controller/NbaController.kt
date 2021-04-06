package com.hongwei.controller

import com.hongwei.model.nba.NbaScheduleRequest
import com.hongwei.service.nba.NbaService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.ResponseBody
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/nba")
class NbaController {
    @Autowired
    private lateinit var nbaService: NbaService

    @RequestMapping(path = ["/teamSchedule.do"])
    @ResponseBody
    fun getScheduleByTeam(@RequestBody nbaScheduleRequest: NbaScheduleRequest): ResponseEntity<*> =
            ResponseEntity.ok(nbaService.getScheduleByTeam(nbaScheduleRequest))
}