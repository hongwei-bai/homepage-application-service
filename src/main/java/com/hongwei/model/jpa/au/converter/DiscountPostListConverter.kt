package com.hongwei.model.jpa.au.converter

import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.hongwei.model.discount.DiscountPost
import java.lang.reflect.Type
import javax.persistence.AttributeConverter
import javax.persistence.Converter

@Converter
class DiscountPostListConverter : AttributeConverter<List<DiscountPost>, String?> {
	override fun convertToDatabaseColumn(stringList: List<DiscountPost>): String? {
		return stringList.let { Gson().toJson(stringList) }
	}

	override fun convertToEntityAttribute(string: String?): List<DiscountPost> {
		val listType: Type = object : TypeToken<List<DiscountPost>>() {}.type
		return if (string != null) Gson().fromJson(string, listType) else emptyList()
	}
}