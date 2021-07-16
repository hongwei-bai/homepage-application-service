package com.hongwei.model.jpa.au

import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query

interface AuGovCovidNotificationRepository : JpaRepository<AuGovCovidNotificationEntity?, Long?> {
    @Query("from AuGovCovidNotificationEntity entity where entity.dayDiff=:dayDiff")
    fun findRecordsByDayDiff(dayDiff: Long): List<AuGovCovidNotificationEntity>

    @Query("from AuGovCovidNotificationEntity entity where entity.postcode=:postcode and entity.dayDiff=:dayDiff")
    fun findRecordsByPostcodeAndDayDiff(postcode: Long, dayDiff: Long): List<AuGovCovidNotificationEntity>
}