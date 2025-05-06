// src/main/java/com/pfa/energy/repository/RoleRepository.java
package com.pfa.energy.repository;
import com.pfa.energy.model.user.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;

public interface RoleRepository extends JpaRepository<Role,Integer> {
    Optional<Role> findByName(String name);
}
