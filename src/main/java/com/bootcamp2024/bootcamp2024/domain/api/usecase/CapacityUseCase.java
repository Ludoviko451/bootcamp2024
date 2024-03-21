package com.bootcamp2024.bootcamp2024.domain.api.usecase;

import com.bootcamp2024.bootcamp2024.adapters.driven.jpa.mysql.exception.DuplicateTechnologyIdsException;
import com.bootcamp2024.bootcamp2024.adapters.driven.jpa.mysql.exception.NoDataFoundException;
import com.bootcamp2024.bootcamp2024.adapters.driven.jpa.mysql.exception.TechnologyIdsIsEmptyException;
import com.bootcamp2024.bootcamp2024.adapters.driven.jpa.mysql.exception.TechnologyIdsSizeIsNotInTheLimitException;
import com.bootcamp2024.bootcamp2024.domain.api.ICapacityServicePort;
import com.bootcamp2024.bootcamp2024.domain.model.Capacity;
import com.bootcamp2024.bootcamp2024.domain.model.Technology;
import com.bootcamp2024.bootcamp2024.domain.spi.ICapacityPersistencePort;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class CapacityUseCase implements ICapacityServicePort {

    public CapacityUseCase(ICapacityPersistencePort capacityPersistencePort) {
        this.capacityPersistencePort = capacityPersistencePort;
    }

    private final ICapacityPersistencePort capacityPersistencePort;

    @Override
    public List<Capacity> getAllCapacity(Integer page, Integer size, String orderBy, int technologies) {
        List<Capacity> capacityList = capacityPersistencePort.getAllCapacity(page, size, orderBy, technologies);
        List<Capacity> capacityFilter = new ArrayList<>();

        for(Capacity capacity: capacityList){
            Capacity filteredCapacity = new Capacity(capacity.getId(), capacity.getName(), capacity.getDescription());
            List<Technology> filteredTechnologies = new ArrayList<>();
            for (int i = 0; i < Math.min(technologies, capacity.getTechnologyList().size()); i++) {
                filteredTechnologies.add(capacity.getTechnologyList().get(i));
            }

            for (Technology technology: filteredTechnologies){
                filteredCapacity.addTechnology(technology);
            }

            capacityFilter.add(filteredCapacity);
        }
        if (capacityList.isEmpty()){
            throw  new NoDataFoundException();
        }


        return capacityFilter;
    }



    @Override
    public void saveCapacity(Capacity capacity, List<Long> technologyIds) {
        // Verificar si la lista de IDs está vacía
        if (technologyIds == null || technologyIds.isEmpty()) {
            throw new TechnologyIdsIsEmptyException();
        }

        // Verificar si hay IDs duplicados
        if (hasDuplicates(technologyIds)) {
            throw new DuplicateTechnologyIdsException();
        }

        // Verificar si el tamaño de la lista está dentro de los límites permitidos
        if (technologyIds.size() < 3 || technologyIds.size() > 20) {
            throw new TechnologyIdsSizeIsNotInTheLimitException();
        }

        // Si todas las comprobaciones son correctas, proceder a guardar
        capacityPersistencePort.saveCapacity(capacity, technologyIds);
    }


//    @Override
//    public void addTechnologyToCapacity(Long capacityId, List<Long> technologyIds) {
//        if (technologyIds.isEmpty()){
//            throw new TechnologyIdsIsEmptyException();
//        } else if (technologyIds.size() >= 3 && technologyIds.size() <= 20 ){
//            // Validar que no haya elementos repetidos en la lista
//            if (hasDuplicates(technologyIds)) {
//                throw new DuplicateTechnologyIdsException();
//            }
//            capacityPersistencePort.addTechnologyToCapacity(capacityId, technologyIds);
//        } else {
//            throw new TechnologyIdsSizeIsNotInTheLimitException();
//        }
//    }


    private boolean hasDuplicates(List<Long> technologyIds) {
        // Crear un conjunto (Set) a partir de la lista para eliminar duplicados
        Set<Long> uniqueIds = new HashSet<>(technologyIds);
        // Si el tamaño del conjunto es diferente al tamaño de la lista, significa que hay duplicados
        return uniqueIds.size() != technologyIds.size();
    }

}
