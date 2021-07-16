package com.hongwei.model.jpa.au

import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query

interface AuSuburbRepository : JpaRepository<AuSuburbEntity?, Long?> {
    @Query("from AuSuburbEntity entity where entity.postcode=:postcode")
    fun findSuburb(postcode: Long): AuSuburbEntity?

    @Query("from AuSuburbEntity entity")
    fun findAllSuburbs(): List<AuSuburbEntity>?
}