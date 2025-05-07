package com.pfa.energy.dto.room;

import jakarta.validation.constraints.NotBlank;

public record RoomRequest(@NotBlank String name) {}