package com.bootcamp2024.bootcamp2024.domain.api.usecase;

import com.bootcamp2024.bootcamp2024.adapters.driven.jpa.mysql.exception.*;
import com.bootcamp2024.bootcamp2024.domain.api.IBootcampServicePort;
import com.bootcamp2024.bootcamp2024.domain.api.ICapacityServicePort;
import com.bootcamp2024.bootcamp2024.domain.model.Bootcamp;
import com.bootcamp2024.bootcamp2024.domain.model.Capacity;
import com.bootcamp2024.bootcamp2024.domain.spi.IBootcampPersistencePort;
import com.bootcamp2024.bootcamp2024.domain.util.ListHelper;
import java.util.List;
import java.util.Optional;


public class BootcampUseCase implements IBootcampServicePort {

    private final ICapacityServicePort capacityServicePort;
    private final IBootcampPersistencePort bootcampPersistencePort;

    public BootcampUseCase(ICapacityServicePort capacityServicePort, IBootcampPersistencePort bootcampPersistencePort) {
        this.capacityServicePort = capacityServicePort;
        this.bootcampPersistencePort = bootcampPersistencePort;
    }


    @Override
    public void saveBootcamp(Bootcamp bootcamp) {
        Optional<Bootcamp> existingBootcamp = bootcampPersistencePort.findBootcampByName(bootcamp.getName());

        if (existingBootcamp.isPresent()){
            throw new BootcampAlreadyExistsException();
        }
        if (ListHelper.hasDuplicatesCapacity(bootcamp.getCapacityList())){
            throw new DuplicateCapacityException();
        }
        if (bootcamp.getCapacityList().isEmpty() || bootcamp.getCapacityList().size() > 4){
            throw new CapacitySizeIsNotInTheLimitException();
        }

        checkCapacity(bootcamp.getCapacityList());
        bootcampPersistencePort.saveBootcamp(bootcamp);
    }

    @Override
    public List<Bootcamp> getAllBootcamp(Integer page, Integer size, String orderBy, boolean capacities, String field) {


        if (page < 0 || size < 0){
            throw new PageAndSizeLessThanZeroException();
        }

        List<Bootcamp> bootcampList = bootcampPersistencePort.getAllBootcamp(page, size, orderBy, capacities, field);

        if (bootcampList.isEmpty()) {
            throw new NoDataFoundException();
        }

        return bootcampList;
    }

    @Override
    public Bootcamp findBootcampById(Long id) {
        return bootcampPersistencePort.findBootcampById(id);
    }

    private void checkCapacity(List<Capacity> capacityList){
        capacityList.forEach(capacity -> capacityServicePort.findCapacityByName(capacity.getName()));
    }
}
