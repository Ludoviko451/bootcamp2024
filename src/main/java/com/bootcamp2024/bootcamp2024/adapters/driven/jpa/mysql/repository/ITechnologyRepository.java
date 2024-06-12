package com.bootcamp2024.bootcamp2024.adapters.driven.jpa.mysql.repository;


import com.bootcamp2024.bootcamp2024.adapters.driven.jpa.mysql.entity.TechnologyEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;



public interface ITechnologyRepository extends JpaRepository<TechnologyEntity, Long> {

    TechnologyEntity findByName(String name);



    Page<TechnologyEntity> findAll(Pageable pageable);
}
