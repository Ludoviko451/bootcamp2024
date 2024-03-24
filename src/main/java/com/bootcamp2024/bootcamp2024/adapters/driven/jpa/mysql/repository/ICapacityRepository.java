package com.bootcamp2024.bootcamp2024.adapters.driven.jpa.mysql.repository;

import com.bootcamp2024.bootcamp2024.adapters.driven.jpa.mysql.entity.CapacityEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface ICapacityRepository extends JpaRepository<CapacityEntity, Long> {
    // Esta consulta JPQL selecciona todas las capacidades (CapacityEntity) de la base de datos.
    // Utiliza LEFT JOIN para unir las capacidades con sus tecnologías asociadas, incluso si algunas capacidades no tienen tecnologías asociadas.
    // El alias "c" se refiere a cada capacidad en CapacityEntity.
    // El alias "t" se refiere a cada tecnología asociada en la lista de tecnologías (technologyList).
    @Query("SELECT c FROM CapacityEntity c LEFT JOIN c.technologyList t GROUP BY c.id ORDER BY COUNT(t) DESC")
    Page<CapacityEntity> findAllOrderByTechnologiesCountDesc(Pageable pageable);

    @Query("SELECT c FROM CapacityEntity c LEFT JOIN c.technologyList t GROUP BY c.id ORDER BY COUNT(t) ASC")
    Page<CapacityEntity> findAllOrderByTechnologiesCountAsc(Pageable pageable);

    CapacityEntity findByName(String name);
}
