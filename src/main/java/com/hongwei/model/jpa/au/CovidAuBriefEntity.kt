package com.hongwei.model.jpa.au

import com.hongwei.model.covid19.CovidAuDayBrief
import com.hongwei.model.jpa.au.converter.CovidAuBriefListConverter
import javax.persistence.*


@Entity
data class CovidAuBriefEntity(
	@Id @Column(nullable = false)
	val dataVersion: Long = 0L,

	@Lob @Convert(converter = CovidAuBriefListConverter::class) @Column(nullable = true)
	var dataByDayBrief: List<CovidAuDayBrief> = emptyList()
)