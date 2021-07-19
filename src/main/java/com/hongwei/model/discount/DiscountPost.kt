package com.hongwei.model.discount

data class DiscountPost(
	val postDateString: String,
	val unixTimeStamp: Long,
	val title: String,
	val author: String,
	val replies: Int,
	val link: String
)