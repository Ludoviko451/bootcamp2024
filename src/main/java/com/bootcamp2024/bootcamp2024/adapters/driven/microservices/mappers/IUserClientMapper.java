package com.bootcamp2024.bootcamp2024.adapters.driven.microservices.mappers;

import com.bootcamp2024.bootcamp2024.adapters.driven.microservices.dto.UserFeignDto;
import com.bootcamp2024.bootcamp2024.domain.model.User;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring",
        unmappedTargetPolicy = ReportingPolicy.IGNORE,
        unmappedSourcePolicy = ReportingPolicy.IGNORE)
public interface IUserClientMapper {

    User toUser(UserFeignDto user);
}
