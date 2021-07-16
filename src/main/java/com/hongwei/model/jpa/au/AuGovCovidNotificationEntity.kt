package com.hongwei.model.jpa.au

import com.hongwei.model.covid19.AuGovCovidLikelyInfectionSource
import com.hongwei.model.jpa.au.converter.DateConverter
import com.hongwei.model.jpa.au.converter.InfectionSourceEnumConverter
import com.hongwei.model.jpa.au.converter.StringListConverter
import java.util.*
import javax.persistence.*


@Entity
data class AuGovCovidNotificationEntity(
        @Id @GeneratedValue
        private var id: Long = 0L,

        @Column(nullable = false)
        val dayDiff: Long = 0L,

        @Lob @Convert(converter = DateConverter::class) @Column(nullable = true)
        val date: Date? = null,

        @Column(nullable = true)
        val postcode: Long? = null,

        @Lob @Convert(converter = StringListConverter::class) @Column(nullable = false)
        val suburbs: List<String> = emptyList(),

        @Column(nullable = true)
        val council: String? = null,

        @Column(nullable = true)
        val greatArea: String? = null,

        @Column(nullable = true)
        val state: String? = null,

        @Lob @Convert(converter = InfectionSourceEnumConverter::class) @Column(nullable = true)
        val likelyInfectionSource: AuGovCovidLikelyInfectionSource? = null
)