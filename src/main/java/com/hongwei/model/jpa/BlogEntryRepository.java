package com.hongwei.model.jpa;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface BlogEntryRepository extends JpaRepository<BlogEntry, Long> {
    @Query("from BlogEntry entry where entry.owner=:owner")
    List<BlogEntry> findByOwner(@Param("owner") String owner);

    @Query("from BlogEntry entry where entry.title=:title and entry.owner=:owner")
    BlogEntry findByTitle(@Param("title") String title, @Param("owner") String owner);

    @Transactional
    @Modifying
    @Query("delete from BlogEntry entry where entry.title=:title and entry.owner=:owner")
    void deleteByTitle(@Param("title") String title, @Param("owner") String owner);
}