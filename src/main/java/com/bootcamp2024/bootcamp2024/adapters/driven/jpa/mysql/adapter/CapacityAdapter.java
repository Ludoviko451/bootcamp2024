package com.bootcamp2024.bootcamp2024.adapters.driven.jpa.mysql.adapter;
import com.bootcamp2024.bootcamp2024.adapters.driven.jpa.mysql.entity.CapacityEntity;
import com.bootcamp2024.bootcamp2024.adapters.driven.jpa.mysql.exception.ParameterNotValidForOrderbyException;
import com.bootcamp2024.bootcamp2024.adapters.driven.jpa.mysql.mapper.ICapacityEntityMapper;
import com.bootcamp2024.bootcamp2024.adapters.driven.jpa.mysql.repository.ICapacityRepository;
import com.bootcamp2024.bootcamp2024.domain.model.Capacity;
import com.bootcamp2024.bootcamp2024.domain.spi.ICapacityPersistencePort;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Component;
import java.util.List;
import java.util.Optional;

@Component
public class CapacityAdapter implements ICapacityPersistencePort {

    private final ICapacityRepository capacityRepository;
    private final ICapacityEntityMapper capacityEntityMapper;




    public CapacityAdapter(ICapacityRepository capacityRepository, ICapacityEntityMapper capacityEntityMapper) {
        this.capacityRepository = capacityRepository;
        this.capacityEntityMapper = capacityEntityMapper;
    }


    @Override
    public List<Capacity> getAllCapacity(Integer page, Integer size, String sortBy, boolean technologies) {
        Pageable pagination;
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


        Page<CapacityEntity> capacityPage;
        if (technologies) {
            if ("desc".equalsIgnoreCase(sortBy)) {
                capacityPage = capacityRepository.findAllOrderByTechnologiesCountDesc(pagination);
            } else {
                capacityPage = capacityRepository.findAllOrderByTechnologiesCountAsc(pagination);
            }
        } else {
            capacityPage = capacityRepository.findAll(pagination);
        }

        List<CapacityEntity> capacityEntityList = capacityPage.getContent();

        return capacityEntityMapper.toModelList(capacityEntityList);
    }



    @Override
    public void saveCapacity(Capacity capacity) {
        capacityRepository.save(capacityEntityMapper.toEntity(capacity));
    }

    @Override
    public Optional<Capacity> findCapacityByName(String name) {
        CapacityEntity capacityEntity = capacityRepository.findByName(name);

        return capacityEntity != null ? Optional.of(capacityEntityMapper.toModel(capacityEntity)) : Optional.empty();
    }


}
