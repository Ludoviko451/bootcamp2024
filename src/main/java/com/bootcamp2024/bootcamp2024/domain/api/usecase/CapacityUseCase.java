package com.bootcamp2024.bootcamp2024.domain.api.usecase;

import com.bootcamp2024.bootcamp2024.adapters.driven.jpa.mysql.exception.*;
import com.bootcamp2024.bootcamp2024.domain.api.ICapacityServicePort;
import com.bootcamp2024.bootcamp2024.domain.api.ITechnologyServicePort;
import com.bootcamp2024.bootcamp2024.domain.model.Capacity;
import com.bootcamp2024.bootcamp2024.domain.model.Technology;
import com.bootcamp2024.bootcamp2024.domain.spi.ICapacityPersistencePort;
import com.bootcamp2024.bootcamp2024.domain.util.ListHelper;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

public class CapacityUseCase implements ICapacityServicePort {

    public CapacityUseCase(ICapacityPersistencePort capacityPersistencePort, ITechnologyServicePort technologyServicePort) {
        this.capacityPersistencePort = capacityPersistencePort;
        this.technologyServicePort = technologyServicePort;
    }

    private final ICapacityPersistencePort capacityPersistencePort;
    private final ITechnologyServicePort technologyServicePort;

    @Override
    public List<Capacity> getAllCapacity(Integer page, Integer size, String orderBy, boolean technologies) {
        if (page < 0 || size < 0){
            throw new PageAndSizeLessThanZeroException();
        }

        List<Capacity> capacityList = capacityPersistencePort.getAllCapacity(page, size, orderBy, technologies);
        if (capacityList.isEmpty()){
            throw  new NoDataFoundException();
        }


        return capacityList;
    }



    @Override
    public void saveCapacity(Capacity capacity) {
        //Verificar si hay una capacidad con el mismo nombre

        Optional<Capacity> existingCapacity = capacityPersistencePort.findCapacityByName(capacity.getName());
        if (existingCapacity.isPresent()){
            throw new CapacityAlreadyExistsException();
        }
        // Verificar si hay tecnologias duplicadas
        if (ListHelper.hasDuplicatesTechnology(capacity.getTechnologyList())) {
            throw new DuplicateTechnologyException();
        }

        // Verificar si el tamaño de la lista está dentro de los límites permitidos
        if (capacity.getTechnologyList().size() < 3 || capacity.getTechnologyList().size() > 20) {
            throw new TechnologySizeIsNotInTheLimitException();
        }

        checkTechnology(capacity.getTechnologyList());
        capacityPersistencePort.saveCapacity(capacity);
    }

    @Override
    public Capacity findCapacityByName(String name) {
        return capacityPersistencePort.findCapacityByName(name)
                .orElseThrow(() -> new CapacityNotFoundException(name));
    }



    private void checkTechnology(List<Technology> technologyList){
        technologyList.forEach(technology -> technologyServicePort.findTechnologyByName(technology.getName()));
    }
//    private boolean hasDuplicates(List<Technology> technologyList) {
//        // Crear un conjunto (Set) a partir de la lista para eliminar duplicados
//        Set<String> uniqueTechnology = new HashSet<>(Set.copyOf(technologyList.stream().map(Technology::getName).toList()));
//        // Si el tamaño del conjunto es diferente al tamaño de la lista, significa que hay duplicados
//        return uniqueTechnology.size() != technologyList.size();
//    }

}
