// src/main/java/com/pfa/energy/repository/RoomRepository.java
package com.pfa.energy.repository;
import com.pfa.energy.model.iot.Room;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;
import java.util.UUID;

public interface RoomRepository extends JpaRepository<Room,UUID> {
    Optional<Room> findByName(String name);
}
