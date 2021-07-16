package com.hongwei.model.jpa.au.converter

import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import java.lang.reflect.Type
import java.util.*
import javax.persistence.AttributeConverter
import javax.persistence.Converter

@Converter
class DateConverter : AttributeConverter<Date, String?> {
    override fun convertToDatabaseColumn(date: Date): String? {
        return date.let { Gson().toJson(date) }
    }

    override fun convertToEntityAttribute(string: String?): Date {
        val type: Type = object : TypeToken<Date>() {}.type
        return if (string != null) Gson().fromJson(string, type) else Date()
    }
}