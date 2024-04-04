package com.bootcamp2024.bootcamp2024.configuration;

import com.bootcamp2024.bootcamp2024.adapters.driven.jpa.mysql.adapter.VersionAdapter;
import com.bootcamp2024.bootcamp2024.adapters.driven.jpa.mysql.mapper.IVersionEntityMapper;
import com.bootcamp2024.bootcamp2024.adapters.driven.jpa.mysql.repository.IVersionRepository;
import com.bootcamp2024.bootcamp2024.domain.api.IVersionServicePort;
import com.bootcamp2024.bootcamp2024.domain.api.usecase.VersionUseCase;
import com.bootcamp2024.bootcamp2024.domain.spi.IBootcampPersistencePort;
import com.bootcamp2024.bootcamp2024.domain.spi.IVersionPersistencePort;
import lombok.AllArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@AllArgsConstructor
@Configuration
public class VersionBeanConfiguration {

    private final IVersionEntityMapper versionEntityMapper;
    private final IVersionRepository versionRepository;
    private final IBootcampPersistencePort bootcampPersistencePort;
    @Bean
    public IVersionPersistencePort versionPersistencePort(){
        return new VersionAdapter(versionRepository, versionEntityMapper);
    }

    @Bean
    public IVersionServicePort versionServicePort(){return new VersionUseCase(versionPersistencePort(), bootcampPersistencePort);
    }
}
