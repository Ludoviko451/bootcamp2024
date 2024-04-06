package com.bootcamp2024.bootcamp2024.adapters.driven.jpa.mysql.adapter;

import com.bootcamp2024.bootcamp2024.adapters.driven.jpa.mysql.entity.BootcampEntity;
import com.bootcamp2024.bootcamp2024.adapters.driven.jpa.mysql.exception.NotValidFieldForVersionException;
import com.bootcamp2024.bootcamp2024.adapters.driven.jpa.mysql.mapper.IBootcampEntityMapper;
import com.bootcamp2024.bootcamp2024.adapters.driven.jpa.mysql.repository.IBootcampRepository;
import com.bootcamp2024.bootcamp2024.domain.model.Bootcamp;
import com.bootcamp2024.bootcamp2024.domain.spi.IBootcampPersistencePort;
import com.bootcamp2024.bootcamp2024.domain.util.ListHelper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
public class BootcampAdapter implements IBootcampPersistencePort {

    private final IBootcampRepository bootcampRepository;
    private final IBootcampEntityMapper bootcampEntityMapper;

    public BootcampAdapter(IBootcampRepository bootcampRepository, IBootcampEntityMapper bootcampEntityMapper) {
        this.bootcampRepository = bootcampRepository;
        this.bootcampEntityMapper = bootcampEntityMapper;
    }

    @Override
    public void saveBootcamp(Bootcamp bootcamp) {
        bootcampRepository.save(bootcampEntityMapper.toEntity(bootcamp));
    }

    @Override
    public List<Bootcamp> getAllBootcamp(Integer page, Integer size, String sortBy, boolean capacities, String field) {
        if (!ListHelper.isValidField(field, "type1")){
            throw new NotValidFieldForVersionException(field);
        }

        Pageable pagination = ListHelper.createPageable(page, size, sortBy, field);


        Page<BootcampEntity> bootcampPage;

        if (capacities){
            if ("desc".equalsIgnoreCase(sortBy)){
                bootcampPage = bootcampRepository.findAllOrderByCapacitiesCountDesc(pagination);
            } else {
                bootcampPage = bootcampRepository.findAllOrderByCapacitiesCountAsc(pagination);
            }
        } else {
            bootcampPage = bootcampRepository.findAll(pagination);
        }
        List<BootcampEntity> bootcampEntityList = bootcampPage.getContent();

        return bootcampEntityMapper.toModelList(bootcampEntityList);
    }

    @Override
    public Optional<Bootcamp> findBootcampByName(String name) {
        BootcampEntity bootcampEntity = bootcampRepository.findByName(name);
        return bootcampEntity != null ? Optional.of(bootcampEntityMapper.toModel(bootcampEntity)) : Optional.empty();
    }

    @Override
    public Bootcamp findBootcampById(Long id) {

        Optional<BootcampEntity> bootcampEntity = bootcampRepository.findById(id);
        return bootcampEntity.map(bootcampEntityMapper::toModel).orElse(null);
    }



}
