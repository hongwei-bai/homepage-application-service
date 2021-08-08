package com.hongwei.model.jpa.au.converter

import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.hongwei.model.covid19.CovidAuDayBrief
import java.lang.reflect.Type
import javax.persistence.AttributeConverter
import javax.persistence.Converter

@Converter
class CovidAuBriefListConverter : AttributeConverter<List<CovidAuDayBrief>, String?> {
	override fun convertToDatabaseColumn(stringList: List<CovidAuDayBrief>): String? {
		return stringList.let { Gson().toJson(stringList) }
	}

	override fun convertToEntityAttribute(string: String?): List<CovidAuDayBrief> {
		val listType: Type = object : TypeToken<List<CovidAuDayBrief>>() {}.type
		return if (string != null) Gson().fromJson(string, listType) else emptyList()
	}
}