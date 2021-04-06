package com.hongwei.service.nba

import com.fasterxml.jackson.databind.ObjectMapper
import com.hongwei.model.nba.NbaScheduleRequest
import com.hongwei.model.nba.NbaScheduleResponse
import org.springframework.stereotype.Service
import java.io.File
import java.io.IOException

@Service
class NbaService {
    fun getScheduleByTeam(request: NbaScheduleRequest): NbaScheduleResponse {
        try {
            val someClassObject: SomeClass = ObjectMapper().readValue(File("../src/main/resources/data.json"), SomeClass::class.java)
            System.out.println(someClassObject)
        } catch (e: IOException) {
            e.printStackTrace()
        }

    }
}