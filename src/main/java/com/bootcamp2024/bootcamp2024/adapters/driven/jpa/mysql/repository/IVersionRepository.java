package com.bootcamp2024.bootcamp2024.adapters.driven.jpa.mysql.repository;

import com.bootcamp2024.bootcamp2024.adapters.driven.jpa.mysql.entity.VersionEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface IVersionRepository extends JpaRepository<VersionEntity, Long> {

    @Query("SELECT v FROM VersionEntity v WHERE v.bootcamp.id IN :bootcampIds")
    Page<VersionEntity> findAllByBootcampIds(@Param("bootcampIds") List<Long> bootcampIds, Pageable pageable);
}
