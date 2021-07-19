package com.hongwei.controller

import com.hongwei.service.DiscountService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/discount")
class DiscountController {
	@Autowired
	private lateinit var discountService: DiscountService

	@PutMapping(path = ["/postList.do"])
	@ResponseBody
	fun generatePostList(): ResponseEntity<*> {
		return ResponseEntity.ok(discountService.queryPostList())
	}

	@GetMapping(path = ["/postList.do"])
	@ResponseBody
	fun getPostList(dataVersion: Long): ResponseEntity<*> {
		return ResponseEntity.ok(discountService.getPostList(dataVersion))
	}
}