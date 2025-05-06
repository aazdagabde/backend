// src/main/java/com/pfa/energy/dto/auth/RegisterRequest.java
package com.pfa.energy.dto.auth;
import jakarta.validation.constraints.*;

public record RegisterRequest(
        @NotBlank String username,
        @NotBlank    String password,
        @Email    @NotBlank      String email,
        @NotBlank   String nom,
        @NotBlank   String prenom,
        @NotBlank String telephone
) {}
