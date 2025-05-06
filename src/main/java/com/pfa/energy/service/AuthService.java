package com.pfa.energy.service;

import com.pfa.energy.dto.auth.*;
import com.pfa.energy.model.user.User;
import com.pfa.energy.repository.UserRepository;
import com.pfa.energy.security.JwtProvider;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.*;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final AuthenticationManager authMgr;
    private final JwtProvider jwtProvider;
    private final UserService userService;
    private final UserRepository userRepository;

    /** Inscription */
    public void register(RegisterRequest r) {
        userService.createUser(r);
    }

    /** Connexion */
    public LoginResponse login(LoginRequest r) {
        // Authentifier
        Authentication auth = authMgr.authenticate(
                new UsernamePasswordAuthenticationToken(r.username(), r.password())
        );

        // Récupérer l’entité User pour son ID
        String username = auth.getName();
        User user = userRepository.findByUsername(username)
                .orElseThrow(() ->
                        new UsernameNotFoundException("Utilisateur introuvable")
                );

        // Générer le JWT
        String token = jwtProvider.generateToken(username);

        return  new LoginResponse(token, user.getId(), user.getUsername(),user.getNom(),user.getPrenom());
    }
}
