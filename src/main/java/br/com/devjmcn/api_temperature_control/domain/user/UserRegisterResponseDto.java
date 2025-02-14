package br.com.devjmcn.api_temperature_control.domain.user;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record UserRegisterResponseDto(
        @NotNull
        @NotBlank
        String id,
        @NotBlank
        @NotBlank
        String name,
        @NotBlank
        @NotBlank
        String email,
        @NotNull
        @NotBlank
        String unitId
){}
