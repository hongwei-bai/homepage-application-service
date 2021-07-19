package com.hongwei.model.jpa.au

import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query

interface DiscountRepository : JpaRepository<DiscountEntity?, Long?> {
	@Query("from DiscountEntity entity order by entity.dataVersion desc")
	fun findDiscountPostList(): DiscountEntity?
}