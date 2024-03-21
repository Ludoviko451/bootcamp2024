package com.bootcamp2024.bootcamp2024.adapters.driven.jpa.mysql.adapter;

import com.bootcamp2024.bootcamp2024.adapters.driven.jpa.mysql.entity.CapacityEntity;
import com.bootcamp2024.bootcamp2024.adapters.driven.jpa.mysql.entity.TechnologyEntity;
import com.bootcamp2024.bootcamp2024.adapters.driven.jpa.mysql.exception.DuplicateTechnologyIdsException;
import com.bootcamp2024.bootcamp2024.adapters.driven.jpa.mysql.exception.NoDataFoundException;
import com.bootcamp2024.bootcamp2024.adapters.driven.jpa.mysql.exception.TechnologyIdsIsEmptyException;
import com.bootcamp2024.bootcamp2024.adapters.driven.jpa.mysql.exception.TechnologyIdsSizeIsNotInTheLimitException;
import com.bootcamp2024.bootcamp2024.adapters.driven.jpa.mysql.mapper.ICapacityEntityMapper;
import com.bootcamp2024.bootcamp2024.adapters.driven.jpa.mysql.mapper.ITechnologyEntityMapper;
import com.bootcamp2024.bootcamp2024.adapters.driven.jpa.mysql.repository.ICapacityRepository;
import com.bootcamp2024.bootcamp2024.adapters.driven.jpa.mysql.repository.ITechnologyRepository;
import com.bootcamp2024.bootcamp2024.domain.model.Capacity;
import com.bootcamp2024.bootcamp2024.domain.model.Technology;
import com.bootcamp2024.bootcamp2024.domain.spi.ICapacityPersistencePort;
import org.aspectj.weaver.patterns.HasMemberTypePattern;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Component;


import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@Component
public class CapacityAdapter implements ICapacityPersistencePort {

    private final ICapacityRepository capacityRepository;
    private final ICapacityEntityMapper capacityEntityMapper;

    private final ITechnologyRepository technologyRepository;

    private final ITechnologyEntityMapper technologyEntityMapper;

    public CapacityAdapter(ICapacityRepository capacityRepository, ICapacityEntityMapper capacityEntityMapper, ITechnologyRepository technologyRepository, ITechnologyEntityMapper technologyEntityMapper) {
        this.capacityRepository = capacityRepository;
        this.capacityEntityMapper = capacityEntityMapper;
        this.technologyRepository = technologyRepository;
        this.technologyEntityMapper = technologyEntityMapper;
    }


    @Override
    public List<Capacity> getAllCapacity(Integer page, Integer size, String orderBy, int technologies) {
        Pageable pagination;
        if (orderBy != null) {
            Sort.Direction direction = Sort.Direction.ASC;
            String sortField = "name"; // Campo predeterminado

            if ("desc".equalsIgnoreCase(orderBy)) {
                direction = Sort.Direction.DESC;
            } else if (!"asc".equalsIgnoreCase(orderBy)) {
                sortField = orderBy;
            }

            pagination = PageRequest.of(page, size, Sort.by(direction, sortField));
        } else {
            pagination = PageRequest.of(page, size);
        }
        List<CapacityEntity> capacityEntityList = capacityRepository.findAll(pagination).getContent();

        return capacityEntityMapper.toModelList(capacityEntityList);
    }

    @Override
    public void saveCapacity(Capacity capacity, List<Long> technologyIds) {
        // Buscar entidades de tecnología basadas en las IDs proporcionadas
        List<TechnologyEntity> technologyEntityList = technologyRepository.findAllById(technologyIds);

        // Si no todas las IDs corresponden a una entidad existente, identifica cuáles faltan
        if(technologyEntityList.size() != technologyIds.size()) {
            // Convertir las IDs encontradas a un conjunto para facilitar la comparación
            Set<Long> foundIds = technologyEntityList.stream()
                    .map(TechnologyEntity::getId)
                    .collect(Collectors.toSet());

            // Identificar las IDs que no fueron encontradas
            List<Long> missingIds = technologyIds.stream()
                    .filter(id -> !foundIds.contains(id))
                    .collect(Collectors.toList());

            // Lanzar una excepción o manejar de acuerdo a la lógica de negocio
            throw new IllegalArgumentException("The following technology IDs do not exist: " + missingIds);
        }

        // Convertir las entidades a modelos de dominio
        List<Technology> technologies = technologyEntityMapper.toModelList(technologyEntityList);

        // Agregar las tecnologías a la capacidad y guardar
        technologies.forEach(capacity::addTechnology);
        capacityRepository.save(capacityEntityMapper.toEntity(capacity));
    }



//
//    @Override
//    public void addTechnologyToCapacity(Long capacityId, List<Long> technologyIds) {
//        // Obtener las entidades de tecnología correspondientes a los IDs proporcionados
//        List<TechnologyEntity> technologyEntities = technologyRepository.findAllById(technologyIds);
//
//        // Mapear las entidades de tecnología a modelos de tecnología
//        List<Technology> technologies = technologyEntityMapper.toModelList(technologyEntities);
//
//        // Buscar la entidad de capacidad por ID
//        Optional<CapacityEntity> optionalCapacityEntity = capacityRepository.findById(capacityId);
//
//
//        if (optionalCapacityEntity.isPresent()) {
//            CapacityEntity capacityEntity = optionalCapacityEntity.get();
//
//            // Mapear la entidad de capacidad a un modelo de capacidad
//            Capacity capacity = capacityEntityMapper.toModel(capacityEntity);
//
//            List<Technology> technologyList = capacity.getTechnologyList();
//
//            if (technologyList.size() == 20) {
//                throw new TechnologyIdsSizeIsNotInTheLimitException();
//            }
//
//            // Agregar las tecnologías al modelo de capacidad
//            for (Technology technology : technologies) {
//                capacity.addTechnology(technology);
//            }
//
//            // Guardar la capacidad actualizada en la base de datos
//            capacityRepository.save(capacityEntityMapper.toEntity(capacity));
//        } else {
//            // Manejar el caso en que no se encuentre la capacidad
//            throw new NoDataFoundException();
//        }
//    }

}
