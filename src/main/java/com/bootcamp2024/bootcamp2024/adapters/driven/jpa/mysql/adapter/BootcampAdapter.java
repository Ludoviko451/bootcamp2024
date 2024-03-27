package com.bootcamp2024.bootcamp2024.adapters.driven.jpa.mysql.adapter;

import com.bootcamp2024.bootcamp2024.adapters.driven.jpa.mysql.entity.BootcampEntity;
import com.bootcamp2024.bootcamp2024.adapters.driven.jpa.mysql.exception.ParameterNotValidForOrderbyException;
import com.bootcamp2024.bootcamp2024.adapters.driven.jpa.mysql.mapper.IBootcampEntityMapper;
import com.bootcamp2024.bootcamp2024.adapters.driven.jpa.mysql.repository.IBootcampRepository;
import com.bootcamp2024.bootcamp2024.domain.model.Bootcamp;
import com.bootcamp2024.bootcamp2024.domain.spi.IBootcampPersistencePort;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import java.util.List;

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
    public List<Bootcamp> getAllBootcamp(Integer page, Integer size, String sortBy, boolean capacities) {
        Pageable pagination;
        //Agregar una validacion de recibir una pagina y un size mayor a 0
        //Agregar campo y direccion de sort

        if (sortBy != null) {
            Sort.Direction direction = Sort.Direction.ASC;
            String sortField = "name"; // Campo predeterminado

            if ("desc".equalsIgnoreCase(sortBy)) {
                direction = Sort.Direction.DESC;
            } else if (!"asc".equalsIgnoreCase(sortBy)) {
                throw new ParameterNotValidForOrderbyException(sortBy);
            }

            pagination = PageRequest.of(page, size, Sort.by(direction, sortField));
        } else {
            pagination = PageRequest.of(page, size);
        }
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
}
