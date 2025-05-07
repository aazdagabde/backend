package com.pfa.energy.dto.room;

import java.time.LocalDateTime;
import java.util.UUID;

public record SensorResponse(
        UUID id,
        String type,
        String ref,
        LocalDateTime installedAt) {}
