package com.hongwei.util

import com.fasterxml.jackson.databind.ObjectMapper
import java.io.File
import java.io.IOException

class OSFileReader {
    fun readFileToString(path: String?, aClazz: Class<*>): String? {
        // create Object Mapper
        val mapper = ObjectMapper()

        // read JSON file and map/convert to java POJO
        try {
            val someClassObject: SomeClass = mapper.readValue(File("../src/main/resources/data.json"), SomeClass::class.java)
            System.out.println(someClassObject)
        } catch (e: IOException) {
            e.printStackTrace()
        }
    }
}