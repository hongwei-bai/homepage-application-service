package com.hongwei.model.jpa.au.converter

import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.hongwei.model.covid19.AuGovCovidLikelyInfectionSource
import java.lang.reflect.Type
import javax.persistence.AttributeConverter
import javax.persistence.Converter

@Converter
class InfectionSourceEnumConverter : AttributeConverter<AuGovCovidLikelyInfectionSource?, String?> {
    override fun convertToDatabaseColumn(obj: AuGovCovidLikelyInfectionSource?): String? {
        return obj?.let { Gson().toJson(obj) }
    }

    override fun convertToEntityAttribute(string: String?): AuGovCovidLikelyInfectionSource? {
        val type: Type = object : TypeToken<AuGovCovidLikelyInfectionSource?>() {}.type
        return if (string != null) Gson().fromJson(string, type) else null
    }
}