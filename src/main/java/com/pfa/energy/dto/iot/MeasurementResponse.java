package com.pfa.energy.dto.iot;

import java.util.UUID;

import java.time.Instant;


public record MeasurementResponse(
        Long id,
        UUID sensorId,
        Double value,
        String unit,
        Instant takenAt) {
}