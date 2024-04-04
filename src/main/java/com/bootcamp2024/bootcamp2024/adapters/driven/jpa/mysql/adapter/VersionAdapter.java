package com.bootcamp2024.bootcamp2024.adapters.driven.jpa.mysql.adapter;

import com.bootcamp2024.bootcamp2024.adapters.driven.jpa.mysql.entity.VersionEntity;
import com.bootcamp2024.bootcamp2024.adapters.driven.jpa.mysql.exception.BootcampNotFoundException;
import com.bootcamp2024.bootcamp2024.adapters.driven.jpa.mysql.exception.NotValidFieldForVersionException;
import com.bootcamp2024.bootcamp2024.adapters.driven.jpa.mysql.exception.ParameterNotValidForOrderbyException;
import com.bootcamp2024.bootcamp2024.adapters.driven.jpa.mysql.mapper.IVersionEntityMapper;
import com.bootcamp2024.bootcamp2024.adapters.driven.jpa.mysql.repository.IVersionRepository;
import com.bootcamp2024.bootcamp2024.domain.model.Version;
import com.bootcamp2024.bootcamp2024.domain.spi.IVersionPersistencePort;
import com.bootcamp2024.bootcamp2024.domain.util.ListHelper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import java.util.List;

public class VersionAdapter implements IVersionPersistencePort {


    private final IVersionRepository versionRepository;
    private final IVersionEntityMapper versionEntityMapper;

    public VersionAdapter(IVersionRepository versionRepository, IVersionEntityMapper versionEntityMapper) {
        this.versionRepository = versionRepository;
        this.versionEntityMapper = versionEntityMapper;
    }

    @Override
    public void saveVersion(Version version) {

        versionRepository.save(versionEntityMapper.toEntity(version));
    }

    @Override
    public List<Version> getAllVersion(Integer page, Integer size, String field, String sortBy, List<Long> bootcampIds) {
        Pageable pagination;

        // Agregar una validación para recibir una página y un tamaño mayor a 0
        // Agregar campo y dirección de ordenamiento
        String sortField = "";

        if (ListHelper.isValidField(field)){
            sortField = field;
        } else {
            throw new NotValidFieldForVersionException(field);
        }

        if (sortBy != null) {
            Sort.Direction direction = Sort.Direction.ASC;

            if ("desc".equalsIgnoreCase(sortBy)) {
                direction = Sort.Direction.DESC;
            } else if (!"asc".equalsIgnoreCase(sortBy)) {
                throw new ParameterNotValidForOrderbyException(sortBy);
            }

            pagination = PageRequest.of(page, size, Sort.by(direction, sortField));
        } else {
            pagination = PageRequest.of(page, size);
        }

        Page<VersionEntity> versionPage;

        if (bootcampIds==null || bootcampIds.isEmpty()){
            versionPage = versionRepository.findAll(pagination);
        } else {
            versionPage = versionRepository.findAllByBootcampIds(bootcampIds, pagination);
        }

        List<VersionEntity> versionEntityList = versionPage.getContent();

        return versionEntityMapper.toModelList(versionEntityList);
    }

}

