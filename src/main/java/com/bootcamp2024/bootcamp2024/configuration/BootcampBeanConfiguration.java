package com.bootcamp2024.bootcamp2024.configuration;

import com.bootcamp2024.bootcamp2024.adapters.driven.jpa.mysql.adapter.BootcampAdapter;
import com.bootcamp2024.bootcamp2024.adapters.driven.jpa.mysql.mapper.IBootcampEntityMapper;
import com.bootcamp2024.bootcamp2024.adapters.driven.jpa.mysql.repository.IBootcampRepository;
import com.bootcamp2024.bootcamp2024.configuration.CapacityBeanConfiguration;
import com.bootcamp2024.bootcamp2024.domain.api.IBootcampServicePort;
import com.bootcamp2024.bootcamp2024.domain.api.usecase.BootcampUseCase;
import com.bootcamp2024.bootcamp2024.domain.spi.IBootcampPersistencePort;
import lombok.AllArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@AllArgsConstructor
@Configuration
public class BootcampBeanConfiguration {

    private final IBootcampEntityMapper bootcampEntityMapper;
    private final IBootcampRepository bootcampRepository;
    private final CapacityBeanConfiguration capacityBeanConfiguration;

    @Bean
    public IBootcampPersistencePort bootcampPersistencePort(){
        return new BootcampAdapter(bootcampRepository, bootcampEntityMapper);
    }
    @Bean
    public IBootcampServicePort bootcampServicePort(){return new BootcampUseCase(capacityBeanConfiguration.capacityServicePort(), bootcampPersistencePort());

    }
}
