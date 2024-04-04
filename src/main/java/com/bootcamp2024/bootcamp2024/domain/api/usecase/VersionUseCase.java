package com.bootcamp2024.bootcamp2024.domain.api.usecase;


import com.bootcamp2024.bootcamp2024.adapters.driven.jpa.mysql.exception.BootcampIdNameMistMatchException;
import com.bootcamp2024.bootcamp2024.adapters.driven.jpa.mysql.exception.BootcampNotFoundException;
import com.bootcamp2024.bootcamp2024.adapters.driven.jpa.mysql.exception.VersionMaximumCapacityPassTheLimitException;
import com.bootcamp2024.bootcamp2024.adapters.driven.jpa.mysql.exception.VersionStartDateIsBeforeEndDateException;
import com.bootcamp2024.bootcamp2024.domain.api.IVersionServicePort;
import com.bootcamp2024.bootcamp2024.domain.model.Bootcamp;
import com.bootcamp2024.bootcamp2024.domain.model.Version;
import com.bootcamp2024.bootcamp2024.domain.spi.IBootcampPersistencePort;
import com.bootcamp2024.bootcamp2024.domain.spi.IVersionPersistencePort;

import java.util.List;
import java.util.Optional;

public class VersionUseCase implements IVersionServicePort {

    private final IVersionPersistencePort versionPersistencePort;
    private final IBootcampPersistencePort bootcampPersistencePort;



    public VersionUseCase(IVersionPersistencePort versionPersistencePort, IBootcampPersistencePort bootcampPersistencePort) {
        this.versionPersistencePort = versionPersistencePort;
        this.bootcampPersistencePort = bootcampPersistencePort;
    }

    @Override
    public void saveVersion(Version version) {

        Bootcamp bootcamp = version.getBootcamp();

        if (version.getMaximumCapacity() < 0 || version.getMaximumCapacity() > 50){
            throw new VersionMaximumCapacityPassTheLimitException(version.getMaximumCapacity());
        }
        if(version.getStartDate().isBefore(version.getEndDate())){
            throw new VersionStartDateIsBeforeEndDateException();
        }

        existBootcamp(bootcamp.getName(), bootcamp.getId());

        versionPersistencePort.saveVersion(version);
    }

    @Override
    public List<Version> getAllVersion(Integer page, Integer size, String field, String sortBy, List<Long> bootcampIds) {
        return versionPersistencePort.getAllVersion(page, size, field, sortBy, bootcampIds);
    }

    public void existBootcamp(String name, Long id) {
        Optional<Bootcamp> existingBootcamp = bootcampPersistencePort.findBootcampByName(name);

        if (existingBootcamp.isPresent()) {
            if (id != existingBootcamp.get().getId()) {
                throw new BootcampIdNameMistMatchException(id, existingBootcamp.get().getId(), name);
            }
        } else {
            throw new BootcampNotFoundException(name);
        }
    }
}