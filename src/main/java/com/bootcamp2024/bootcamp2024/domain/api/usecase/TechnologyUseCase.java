package com.bootcamp2024.bootcamp2024.domain.api.usecase;



import com.bootcamp2024.bootcamp2024.adapters.driven.jpa.mysql.exception.NoDataFoundException;
import com.bootcamp2024.bootcamp2024.adapters.driven.jpa.mysql.exception.PageAndSizeLessThanZeroException;
import com.bootcamp2024.bootcamp2024.adapters.driven.jpa.mysql.exception.TechnologyAlreadyExistsException;
import com.bootcamp2024.bootcamp2024.adapters.driven.jpa.mysql.exception.TechnologyNotFoundException;
import com.bootcamp2024.bootcamp2024.domain.api.ITechnologyServicePort;
import com.bootcamp2024.bootcamp2024.domain.model.Technology;
import com.bootcamp2024.bootcamp2024.domain.spi.ITechnologyPersistencePort;

import java.util.List;
import java.util.Optional;


public class TechnologyUseCase implements ITechnologyServicePort {
    private final ITechnologyPersistencePort tecnologiaPersistencePort;

    public TechnologyUseCase(ITechnologyPersistencePort tecnologiaPersistencePort) {
        this.tecnologiaPersistencePort = tecnologiaPersistencePort;
    }


    @Override
    public void deleteTechnology(Long id) {
        tecnologiaPersistencePort.deleteTechnology(id);
    }

    @Override
    public void saveTechnology(Technology technology) {

        Optional<Technology> existingTecnologia = tecnologiaPersistencePort.findByName(technology.getName());
        if (existingTecnologia.isPresent()) {
            throw new TechnologyAlreadyExistsException();
        }

        tecnologiaPersistencePort.saveTechnology(technology);
    }

    @Override
    public Technology updateTechnology(Technology technology) {
        return tecnologiaPersistencePort.updateTechnology(technology);
    }


    @Override
    public List<Technology> getAllTechnology(Integer page, Integer size, String sortBy, String field) {
        if (page < 0 || size < 0){
            throw new PageAndSizeLessThanZeroException();
        }
        List<Technology> technologyList = tecnologiaPersistencePort.getAllTechnology(page, size, sortBy, field);

        if (technologyList.isEmpty()){
            throw new NoDataFoundException();
        }
        return technologyList;
    }

    @Override
    public Technology findTechnologyByName(String name) {
        return tecnologiaPersistencePort.findByName(name)
                .orElseThrow(() -> new TechnologyNotFoundException(name));
    }
}

