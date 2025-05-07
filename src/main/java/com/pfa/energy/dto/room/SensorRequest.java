package com.pfa.energy.dto.room;

import jakarta.validation.constraints.NotBlank;

import java.time.LocalDateTime;
import java.util.UUID;

public record SensorRequest(
        @NotBlank String room,      // nom de la salle — plus simple côté front
        @NotBlank String type,
        @NotBlank String ref) {}
