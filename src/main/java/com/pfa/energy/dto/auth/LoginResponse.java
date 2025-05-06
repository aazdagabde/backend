// src/main/java/com/pfa/energy/dto/auth/LoginResponse.java
package com.pfa.energy.dto.auth;
import java.util.UUID;

public record LoginResponse(String token, UUID userId, String username , String nom , String prenom) {}
