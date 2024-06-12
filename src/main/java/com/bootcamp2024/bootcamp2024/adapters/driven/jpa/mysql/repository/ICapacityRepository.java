package com.bootcamp2024.bootcamp2024.adapters.driven.jpa.mysql.repository;

import com.bootcamp2024.bootcamp2024.adapters.driven.jpa.mysql.entity.CapacityEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface ICapacityRepository extends JpaRepository<CapacityEntity, Long> {

    @Query("SELECT c FROM CapacityEntity c LEFT JOIN c.technologyList t GROUP BY c.id ORDER BY COUNT(t) DESC")
    Page<CapacityEntity> findAllOrderByTechnologiesCountDesc(Pageable pageable);

    @Query("SELECT c FROM CapacityEntity c LEFT JOIN c.technologyList t GROUP BY c.id ORDER BY COUNT(t) ASC")
    Page<CapacityEntity> findAllOrderByTechnologiesCountAsc(Pageable pageable);

    CapacityEntity findByName(String name);
}
