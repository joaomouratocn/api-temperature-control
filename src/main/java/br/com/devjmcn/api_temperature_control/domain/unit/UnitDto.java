package br.com.devjmcn.api_temperature_control.domain.unit;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record UnitDto(
        String id,

        @NotNull
        @NotBlank
        @NotBlank
        String name
) {}
