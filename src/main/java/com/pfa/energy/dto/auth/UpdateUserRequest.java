// src/main/java/com/pfa/energy/dto/auth/UpdateUserRequest.java
package com.pfa.energy.dto.auth;

import jakarta.validation.constraints.*;

public record UpdateUserRequest(
        @NotBlank @Size(max=50)  String firstName,
        @NotBlank @Size(max=50)  String lastName,
        @Pattern(regexp="^[+0-9 ]{6,20}$") String phone
) {}
