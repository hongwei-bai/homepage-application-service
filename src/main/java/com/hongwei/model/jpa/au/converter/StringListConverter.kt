package com.hongwei.model.jpa.au.converter

import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import java.lang.reflect.Type
import javax.persistence.AttributeConverter
import javax.persistence.Converter

@Converter
class StringListConverter : AttributeConverter<List<String>, String?> {
    override fun convertToDatabaseColumn(stringList: List<String>): String? {
        return stringList.let { Gson().toJson(stringList) }
    }

    override fun convertToEntityAttribute(string: String?): List<String> {
        val listType: Type = object : TypeToken<List<String>>() {}.type
        return if (string != null) Gson().fromJson(string, listType) else emptyList()
    }
}