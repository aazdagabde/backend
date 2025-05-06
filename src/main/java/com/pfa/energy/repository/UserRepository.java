// src/main/java/com/pfa/energy/repository/UserRepository.java
package com.pfa.energy.repository;
import com.pfa.energy.model.user.User;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.*;

public interface UserRepository extends JpaRepository<User,UUID> {
    Optional<User> findByUsername(String username);
    boolean existsByEmail(String email);
}
