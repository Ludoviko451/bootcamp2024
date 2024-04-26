package com.bootcamp2024.bootcamp2024.adapters.driven.microservices.client;

import com.bootcamp2024.bootcamp2024.adapters.driven.microservices.dto.UserFeignDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(name = "user", url = "http://localhost:8090/user")
public interface IUserFeignClient {

    @GetMapping("/userByEmail")
    UserFeignDto getUserByMail(@RequestParam("email") String email);
}
