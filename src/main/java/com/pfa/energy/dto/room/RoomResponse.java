package com.pfa.energy.dto.room;

import java.util.List;
import java.util.UUID;

public record RoomResponse(
        UUID id,
        String name,
        List<SensorResponse> sensors) {}
