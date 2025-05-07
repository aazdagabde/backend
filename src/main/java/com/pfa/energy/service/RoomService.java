package com.pfa.energy.service;

import com.pfa.energy.dto.room.RoomRequest;
import com.pfa.energy.dto.room.RoomResponse;
import com.pfa.energy.dto.room.SensorResponse;
import com.pfa.energy.model.iot.Room;
import com.pfa.energy.repository.RoomRepository;
import com.pfa.energy.repository.SensorRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
@Transactional
public class RoomService {

    private final RoomRepository roomRepo;
    private final SensorRepository sensorRepo;

    public RoomResponse create(RoomRequest req) {
        if (roomRepo.findByName(req.name()).isPresent())
            throw new ResponseStatusException(HttpStatus.CONFLICT, "La salle existe déjà");

        Room room = roomRepo.save(new Room(UUID.randomUUID(), req.name()));
        return toDto(room);
    }

    public List<RoomResponse> findAll() {
        return roomRepo.findAll(Sort.by("name"))
                .stream()
                .map(this::toDto)
                .toList();
    }

    /* -------- mapping utilitaire -------- */
    private RoomResponse toDto(Room room) {
        List<SensorResponse> sensors = sensorRepo.findByRoom(room)
                .stream()
                .map(s -> new SensorResponse(
                        s.getId(), s.getType(), s.getRef(), s.getInstalledAt()))
                .toList();
        return new RoomResponse(room.getId(), room.getName(), sensors);
    }
}
