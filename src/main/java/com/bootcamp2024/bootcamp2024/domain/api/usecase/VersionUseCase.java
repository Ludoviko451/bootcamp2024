package com.bootcamp2024.bootcamp2024.domain.api.usecase;


import com.bootcamp2024.bootcamp2024.adapters.driven.jpa.mysql.exception.*;
import com.bootcamp2024.bootcamp2024.domain.api.IVersionServicePort;
import com.bootcamp2024.bootcamp2024.domain.model.Bootcamp;
import com.bootcamp2024.bootcamp2024.domain.model.Version;
import com.bootcamp2024.bootcamp2024.domain.spi.IBootcampPersistencePort;
import com.bootcamp2024.bootcamp2024.domain.spi.IVersionPersistencePort;
import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.List;


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


        LocalDate today = LocalDate.now();


        String startDateStr = version.getStartDate();
        String endDateStr = version.getEndDate();

        LocalDate startDate;
        LocalDate endDate;

        try {
            // Parsear las cadenas de fecha a objetos LocalDate
            startDate = LocalDate.parse(startDateStr);
            endDate = LocalDate.parse(endDateStr);
        } catch (DateTimeParseException e) {
            throw new VersionDateParseException();
        }

        if (version.getMaximumCapacity() < 0 || version.getMaximumCapacity() > 50){
            throw new VersionMaximumCapacityPassTheLimitException(version.getMaximumCapacity());
        }
        if(endDate.isBefore(startDate)){
            throw new VersionEndDateIsBeforeStartDateException();
        }

        if(startDate.isBefore(today)){
                throw new DateVersionBeforeTodayException(startDate);
            }

        if(endDate.isBefore(today)){
                throw new DateVersionBeforeTodayException(endDate);
            }


        existBootcamp(bootcamp.getId());

        versionPersistencePort.saveVersion(version);
    }

    @Override
    public List<Version> getAllVersion(Integer page, Integer size, String field, String sortBy, List<Long> bootcampIds) {
        if (page < 0 || size < 0){
            throw new PageAndSizeLessThanZeroException();
        }
        checkBootcamps(bootcampIds);
        return versionPersistencePort.getAllVersion(page, size, field, sortBy, bootcampIds);
    }

    public void checkBootcamps(List<Long> bootcampIds) {
        if (bootcampIds != null){
        for (Long id : bootcampIds) {
            // Utiliza el método findBootcampById para verificar cada bootcamp por su ID
            Bootcamp bootcamp = bootcampPersistencePort.findBootcampById(id);
            if (bootcamp == null) {
                throw new BootcampNotFoundException(id);
            }
        }
        }
    }

    public void existBootcamp(Long id) {
        Bootcamp existingBootcamp = bootcampPersistencePort.findBootcampById(id);

        if (existingBootcamp == null) {
            throw new BootcampNotFoundException(id);
        }
    }
}