package com.pfa.energy.dto.iot;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDateTime;
import java.util.UUID;


public record MeasurementRequest(
        @NotNull UUID sensorId,
        @NotNull Double value,
        @NotNull String unit) {
}
