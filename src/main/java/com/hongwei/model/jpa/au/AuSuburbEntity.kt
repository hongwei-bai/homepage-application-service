package com.hongwei.model.jpa.au

import com.hongwei.model.jpa.au.converter.StringListConverter
import javax.persistence.*


@Entity
data class AuSuburbEntity(
        @Id @Column(nullable = false)
        val postcode: Long = 0L,

        @Lob @Convert(converter = StringListConverter::class) @Column(nullable = false)
        val suburbs: MutableList<String> = mutableListOf(),

        @Column(nullable = false)
        val state: String = "",

        @Column(nullable = false)
        val stateCode: String = "",

        @Column(nullable = false)
        val latitude: Double = 0.0,

        @Column(nullable = false)
        val longitude: Double = 0.0,

        @Column(nullable = false)
        val accuracy: Int = 0
)