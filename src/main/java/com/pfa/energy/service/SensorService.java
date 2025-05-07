package com.pfa.energy.service;

import com.pfa.energy.dto.room.SensorRequest;
import com.pfa.energy.dto.room.SensorResponse;
import com.pfa.energy.model.iot.Room;
import com.pfa.energy.model.iot.Sensor;
import com.pfa.energy.repository.RoomRepository;
import com.pfa.energy.repository.SensorRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDateTime;
import java.util.UUID;

@Service
@RequiredArgsConstructor
@Transactional
public class SensorService {

    private final RoomRepository roomRepo;
    private final SensorRepository sensorRepo;

    public SensorResponse add(SensorRequest req) {
        Room room = roomRepo.findByName(req.room())
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.BAD_REQUEST, "Salle introuvable"));

        Sensor sensor = new Sensor(UUID.randomUUID(), room, req.type(), req.ref(), LocalDateTime.now());
        sensorRepo.save(sensor);

        return new SensorResponse(sensor.getId(), sensor.getType(), sensor.getRef(), sensor.getInstalledAt());
    }
}

