package com.bootcamp2024.bootcamp2024.configuration;

import com.bootcamp2024.bootcamp2024.adapters.driven.jpa.mysql.adapter.TechnologyAdapter;
import com.bootcamp2024.bootcamp2024.adapters.driven.jpa.mysql.mapper.ITechnologyEntityMapper;
import com.bootcamp2024.bootcamp2024.domain.api.ITechnologyServicePort;
import com.bootcamp2024.bootcamp2024.domain.api.usecase.TechnologyUseCase;
import com.bootcamp2024.bootcamp2024.domain.spi.ITechnologyPersistencePort;
import com.bootcamp2024.bootcamp2024.adapters.driven.jpa.mysql.repository.ITechnologyRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@RequiredArgsConstructor
public class BeanConfiguration {

    private final ITechnologyEntityMapper technologyEntityMapper;
    private final ITechnologyRepository technologyRepository;



    @Bean
    public ITechnologyPersistencePort tecnologiaPersistencePort() {
        return new TechnologyAdapter(technologyRepository, technologyEntityMapper);
    }

    @Bean
    public ITechnologyServicePort tecnologiaServicePort() {
        return new TechnologyUseCase(tecnologiaPersistencePort());
    }


}
