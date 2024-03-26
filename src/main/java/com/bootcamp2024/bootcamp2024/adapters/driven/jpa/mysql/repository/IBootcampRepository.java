package com.bootcamp2024.bootcamp2024.adapters.driven.jpa.mysql.repository;

import com.bootcamp2024.bootcamp2024.adapters.driven.jpa.mysql.entity.BootcampEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface IBootcampRepository extends JpaRepository <BootcampEntity, Long> {

    @Query("SELECT b from BootcampEntity  b LEFT JOIN b.capacityList c GROUP BY b.id ORDER BY COUNT(c) DESC")
    Page<BootcampEntity> findAllOrderByCapacitiesCountDesc(Pageable pageable);

    @Query("SELECT b from BootcampEntity b LEFT JOIN b.capacityList c GROUP BY b.id ORDER BY COUNT(c) ASC")
    Page<BootcampEntity> findAllOrderByCapacitiesCountAsc(Pageable pageable);

}
