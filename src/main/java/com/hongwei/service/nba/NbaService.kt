package com.hongwei.service.nba

import com.fasterxml.jackson.databind.DeserializationFeature
import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.module.kotlin.KotlinModule
import com.hongwei.model.nba.NbaScheduleRequest
import com.hongwei.model.nba.NbaScheduleResponse
import com.hongwei.model.nba.TeamScheduleMapper
import com.hongwei.model.nba.TeamScheduleSource
import org.apache.log4j.LogManager
import org.apache.log4j.Logger
import org.springframework.beans.factory.annotation.Value
import org.springframework.stereotype.Service
import java.io.File
import java.io.IOException

@Service
class NbaService {
    private val logger: Logger = LogManager.getLogger(NbaService::class.java)

    @Value("\${nba.json.path}")
    private lateinit var nbaJsonPath: String

    @Throws(IOException::class)
    fun getScheduleByTeam(request: NbaScheduleRequest): NbaScheduleResponse {
        val jsonPath = nbaJsonPath.replace("{team}", request.team!!)
        val teamSchedule = ObjectMapper().registerModule(KotlinModule())
                .disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES)
                .readValue(File(jsonPath), TeamScheduleSource::class.java)
        return NbaScheduleResponse(TeamScheduleMapper.map(teamSchedule))
    }

    @Throws(IOException::class)
    fun getScheduleByTeam(team: String, currentDataVersion: Long): NbaScheduleResponse? {
        val jsonPath = nbaJsonPath.replace("{team}", team)
        val teamSchedule = ObjectMapper().registerModule(KotlinModule())
                .disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES)
                .readValue(File(jsonPath), TeamScheduleSource::class.java)
        return if ((teamSchedule.dataVersion ?: 0) > currentDataVersion) {
            NbaScheduleResponse(TeamScheduleMapper.map(teamSchedule))
        } else {
            null
        }
    }
}