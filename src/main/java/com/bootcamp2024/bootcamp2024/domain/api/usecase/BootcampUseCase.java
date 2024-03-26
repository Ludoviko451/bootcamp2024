package com.bootcamp2024.bootcamp2024.domain.api.usecase;

import com.bootcamp2024.bootcamp2024.adapters.driven.jpa.mysql.exception.CapacitySizeIsNotInTheLimitException;
import com.bootcamp2024.bootcamp2024.adapters.driven.jpa.mysql.exception.DuplicateCapacityException;
import com.bootcamp2024.bootcamp2024.adapters.driven.jpa.mysql.exception.NoDataFoundException;
import com.bootcamp2024.bootcamp2024.domain.api.IBootcampServicePort;
import com.bootcamp2024.bootcamp2024.domain.api.ICapacityServicePort;
import com.bootcamp2024.bootcamp2024.domain.model.Bootcamp;
import com.bootcamp2024.bootcamp2024.domain.model.Capacity;
import com.bootcamp2024.bootcamp2024.domain.spi.IBootcampPersistencePort;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class BootcampUseCase implements IBootcampServicePort {

    private final ICapacityServicePort capacityServicePort;
    private final IBootcampPersistencePort bootcampPersistencePort;

    public BootcampUseCase(ICapacityServicePort capacityServicePort, IBootcampPersistencePort bootcampPersistencePort) {
        this.capacityServicePort = capacityServicePort;
        this.bootcampPersistencePort = bootcampPersistencePort;
    }


    @Override
    public void saveBootcamp(Bootcamp bootcamp) {

        if (hasDuplicates(bootcamp.getCapacityList())){
            throw new DuplicateCapacityException();
        }
        if (bootcamp.getCapacityList().isEmpty() || bootcamp.getCapacityList().size() > 4){
            throw new CapacitySizeIsNotInTheLimitException();
        }

        checkCapacity(bootcamp.getCapacityList());
        bootcampPersistencePort.saveBootcamp(bootcamp);
    }

    @Override
    public List<Bootcamp> getAllBootcamp(Integer page, Integer size, String orderBy, boolean capacities) {

        List<Bootcamp> bootcampList = bootcampPersistencePort.getAllBootcamp(page, size, orderBy, capacities);

        if (bootcampList.isEmpty()) {
            throw new NoDataFoundException();
        }

        return bootcampList;
    }

    private void checkCapacity(List<Capacity> capacityList){
        capacityList.forEach(capacity -> capacityServicePort.findCapacityByName(capacity.getName()));
    }
    private boolean hasDuplicates(List<Capacity> capacityList){

        Set<String>  uniqueCapacity = new HashSet<>(Set.copyOf(capacityList.stream().map(Capacity::getName).toList()));

        return  uniqueCapacity.size() != capacityList.size();
    }
}
