// src/main/java/com/pfa/energy/dto/auth/LoginRequest.java
package com.pfa.energy.dto.auth;
import jakarta.validation.constraints.*;

public record LoginRequest(
        @NotBlank String username,
        @NotBlank String password
) {}
