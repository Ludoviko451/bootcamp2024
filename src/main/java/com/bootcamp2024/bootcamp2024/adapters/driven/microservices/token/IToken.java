package com.bootcamp2024.bootcamp2024.adapters.driven.microservices.token;

public interface IToken {

    String getBearerToken();

    String getEmail(String token);
}
