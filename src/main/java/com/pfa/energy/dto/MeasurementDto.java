// src/main/java/com/pfa/energy/dto/MeasurementDto.java
package com.pfa.energy.dto;

import jakarta.validation.constraints.*;
public record MeasurementDto(
        @NotBlank String room,
        @PositiveOrZero double current,
        @PositiveOrZero long timestamp
) {}
