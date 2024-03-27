package com.bootcamp2024.bootcamp2024.configuration;


import com.bootcamp2024.bootcamp2024.adapters.driven.jpa.mysql.adapter.CapacityAdapter;
import com.bootcamp2024.bootcamp2024.adapters.driven.jpa.mysql.mapper.ICapacityEntityMapper;
import com.bootcamp2024.bootcamp2024.adapters.driven.jpa.mysql.repository.ICapacityRepository;
import com.bootcamp2024.bootcamp2024.domain.api.ICapacityServicePort;
import com.bootcamp2024.bootcamp2024.domain.api.usecase.CapacityUseCase;
import com.bootcamp2024.bootcamp2024.domain.spi.ICapacityPersistencePort;
import lombok.AllArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@AllArgsConstructor
public class CapacityBeanConfiguration {

    private final ICapacityEntityMapper capacityEntityMapper;
    private final ICapacityRepository capacityRepository;
    private final TechnologyBeanConfiguration technologyBeanConfiguration;

    @Bean
    public ICapacityPersistencePort capacityPersistencePort(){
        return new CapacityAdapter(capacityRepository, capacityEntityMapper);
    }

    @Bean
    public ICapacityServicePort capacityServicePort(){ return new CapacityUseCase(capacityPersistencePort(), technologyBeanConfiguration.tecnologiaServicePort());
    }
}
