// src/main/java/com/pfa/energy/repository/SensorRepository.java
package com.pfa.energy.repository;
import com.pfa.energy.model.iot.Sensor;
import com.pfa.energy.model.iot.Room;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface SensorRepository extends JpaRepository<Sensor, UUID> {
    Optional<Sensor> findByRoomAndType(Room room, String type);
    Optional<Sensor> findByRef(String ref);
    List<Sensor> findByRoom(Room room);           // ②  pour construire RoomResponse
}