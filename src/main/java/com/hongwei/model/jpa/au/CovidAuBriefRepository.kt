package com.hongwei.model.jpa.au

import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query

interface CovidAuBriefRepository : JpaRepository<CovidAuBriefEntity?, Long?> {
	@Query("from CovidAuBriefEntity entity order by entity.dataVersion desc")
	fun findRecentRecord(): CovidAuBriefEntity?
}