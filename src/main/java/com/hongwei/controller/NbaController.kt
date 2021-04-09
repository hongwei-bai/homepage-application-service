package com.hongwei.controller

import com.hongwei.constants.ResetContent
import com.hongwei.model.nba.NbaScheduleRequest
import com.hongwei.service.nba.NbaService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/nba")
class NbaController {
    @Autowired
    private lateinit var nbaService: NbaService

    @RequestMapping(path = ["/teamScheduleCompatible.do"])
    @ResponseBody
    fun getScheduleByTeam(@RequestBody nbaScheduleRequest: NbaScheduleRequest): ResponseEntity<*> =
            ResponseEntity.ok(nbaService.getScheduleByTeam(nbaScheduleRequest))

    @GetMapping(path = ["/teamSchedule.do"])
    @ResponseBody
    fun testGetTeamSchedule(team: String, dataVersion: Long): ResponseEntity<*> =
            nbaService.getScheduleByTeam(team, dataVersion)?.let {
                ResponseEntity.ok(it)
            } ?: throw ResetContent
}