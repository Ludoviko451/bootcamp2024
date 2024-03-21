package com.bootcamp2024.bootcamp2024.adapters.driven.jpa.mysql.repository;

import com.bootcamp2024.bootcamp2024.adapters.driven.jpa.mysql.entity.CapacityEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ICapacityRepository extends JpaRepository<CapacityEntity, Long> {
}
