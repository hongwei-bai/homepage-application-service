package com.hongwei.service

import com.hongwei.constants.NoContent
import com.hongwei.constants.ResetContent
import com.hongwei.model.discount.DiscountPost
import com.hongwei.model.discount.SiteOurstepsDiscountChannelQuery
import com.hongwei.model.jpa.au.DiscountEntity
import com.hongwei.model.jpa.au.DiscountRepository
import com.hongwei.util.DateTimeParseUtil
import com.hongwei.util.TimeStampUtil
import com.hongwei.util.curl.CUrlWrapper
import org.apache.log4j.LogManager
import org.apache.log4j.Logger
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service


@Service
class DiscountService {
	private val logger: Logger = LogManager.getLogger(DiscountService::class.java)

	@Autowired
	private lateinit var discountRepository: DiscountRepository

	fun getPostList(dataVersion: Long): DiscountEntity? =
		discountRepository.findDiscountPostList()?.let {
			if (dataVersion < it.dataVersion) {
				it
			} else throw ResetContent
		} ?: throw NoContent

	fun queryPostList(pages: Int = 2): DiscountEntity? {
		val postList = List(pages) { it }.mapNotNull { page ->
			queryPostListByPage(page)
		}.flatten()
		val discountEntityDb = discountRepository.findDiscountPostList()
		if (postList != discountEntityDb?.posts) {
			discountRepository.deleteAll()
			val entity = DiscountEntity(
				dataVersion = TimeStampUtil.getTimeVersionWithMinute(),
				posts = postList
			)
			discountRepository.save(entity)
			return entity
		}
		return null
	}

	private fun queryPostListByPage(page: Int): List<DiscountPost>? {
		val htmlDoc = CUrlWrapper.curl(SiteOurstepsDiscountChannelQuery().page(page).build())
		val postList = htmlDoc?.getElementsByClass("thlist")
			?.firstOrNull()
			?.getElementsByTag("li")
			?.mapNotNull {
				val link = it.getElementsByTag("a")?.attr("href")
				val title = it.getElementsByTag("h1")?.text()
				val moreInfo = it.getElementsByTag("p")?.text()
				val index1 = moreInfo?.indexOf("-")
				val author = index1?.let { moreInfo?.substring(0, index1) }
				val moreInfo2 = index1?.let { moreInfo.substring(index1 + 1) }
				val array = moreInfo2?.split(" ")
				val dateString = array?.firstOrNull()
				val replies = array?.get(1)
				val unixTimeStamp = dateString?.let { DateTimeParseUtil.parseDate(dateString)?.time }
				if (link != null && title != null && author != null && dateString != null && replies != null && unixTimeStamp != null) {
					DiscountPost(
						postDateString = dateString,
						unixTimeStamp = unixTimeStamp,
						title = title,
						author = author,
						replies = replies.toInt(),
						link = link
					)
				} else null
			}

		return postList
	}
}