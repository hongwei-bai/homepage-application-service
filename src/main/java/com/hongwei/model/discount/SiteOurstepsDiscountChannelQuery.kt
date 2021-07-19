package com.hongwei.model.discount

class SiteOurstepsDiscountChannelQuery {
	private var discountChannelBaseUrl = "https://www.oursteps.com.au/bbs/forum.php?mod=forumdisplay&fid=89&page="

	private var pageNumber = 1

	private var discountChannelPostBaseUrl = "https://www.oursteps.com.au/bbs/"

	fun page(page: Int = 1): SiteOurstepsDiscountChannelQuery {
		pageNumber = page
		return this
	}

	fun build(): String = "$discountChannelBaseUrl$pageNumber"

	fun buildPostUrl(rawLink: String): String = "$discountChannelPostBaseUrl${rawLink.replace("&amp;", "&")}"
}