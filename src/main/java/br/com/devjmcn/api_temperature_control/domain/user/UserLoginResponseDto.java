package br.com.devjmcn.api_temperature_control.domain.user;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record UserLoginResponseDto(
        @NotNull
        @NotBlank
        String token,

        @NotNull
        @NotBlank
        String name
){}
