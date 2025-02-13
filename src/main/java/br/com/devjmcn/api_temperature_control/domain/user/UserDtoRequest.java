package br.com.devjmcn.api_temperature_control.domain.user;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record UserDtoRequest(
        String id,
        @NotNull
        @NotBlank
        String email,
        @NotNull
        @NotBlank
        String name,
        @NotNull
        @NotBlank
        String password,
        @NotBlank
        @NotNull
        Integer unitId
) {}
