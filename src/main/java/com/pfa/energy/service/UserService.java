package com.pfa.energy.service;

import com.pfa.energy.dto.auth.RegisterRequest;
import com.pfa.energy.model.user.Role;
import com.pfa.energy.model.user.User;
import com.pfa.energy.repository.RoleRepository;
import com.pfa.energy.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepo;
    private final RoleRepository roleRepo;
    private final PasswordEncoder encoder;

    public void createUser(RegisterRequest r) {
        if (userRepo.existsByEmail(r.email())) {
            throw new IllegalStateException("Email déjà utilisé");
        }
        Role role = roleRepo.findByName("ROLE_USER")
                .orElseGet(() -> roleRepo.save(new Role(null, "ROLE_USER")));

        User user = new User();
        user.setUsername(r.username());
        user.setEmail(r.email());
        user.setNom(r.nom());
        user.setPrenom(r.prenom());
        user.setTelephone(r.telephone());
        user.setPassword(encoder.encode(r.password()));
        user.getRoles().add(role);

        userRepo.save(user);
    }
}
