// src/main/java/com/pfa/energy/service/MeasurementService.java
package com.pfa.energy.service;
import com.pfa.energy.dto.MeasurementDto;
import com.pfa.energy.model.iot.*;
import com.pfa.energy.repository.*;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.time.Instant; import java.time.LocalDateTime; import java.util.UUID;

@Service @RequiredArgsConstructor @Transactional
public class MeasurementService {
    private final RoomRepository roomRepo;
    private final SensorRepository sensorRepo;
    private final MeasurementRepository measRepo;

    public void ingest(MeasurementDto dto) {
        var room = roomRepo.findByName(dto.room())
                .orElseGet(() -> roomRepo.save(new Room(UUID.randomUUID(), dto.room())));
        var sensor = sensorRepo.findByRoomAndType(room,"CURRENT")
                .orElseGet(() -> sensorRepo.save(new Sensor(
                        UUID.randomUUID(),room,"CURRENT","ct-001",LocalDateTime.now())));
        var m = new Measurement();
        m.setSensor(sensor);
        m.setValue(dto.current());
        m.setUnit("A");
        m.setTakenAt(Instant.ofEpochSecond(dto.timestamp()));
        measRepo.save(m);
    }
}
