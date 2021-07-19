package com.hongwei.model.jpa.au

import com.hongwei.model.discount.DiscountPost
import com.hongwei.model.jpa.au.converter.DiscountPostListConverter
import javax.persistence.*

@Entity
data class DiscountEntity(
	@Id @Column(nullable = false)
	val dataVersion: Long = 0L,

	@Lob @Convert(converter = DiscountPostListConverter::class) @Column(nullable = true)
	val posts: List<DiscountPost> = emptyList()
)