package com.pfa.energy.controller.api;

import com.pfa.energy.dto.room.RoomRequest;
import com.pfa.energy.dto.room.RoomResponse;
import com.pfa.energy.service.RoomService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/rooms") @RequiredArgsConstructor
public class RoomController {

    private final RoomService roomSvc;

    /** 1️⃣  Create Room */
    @PostMapping
    @PreAuthorize("hasRole('ADMIN')")           // facultatif selon votre politique de sécurité
    public ResponseEntity<RoomResponse> create(@Valid @RequestBody RoomRequest req) {
        RoomResponse created = roomSvc.create(req);
        return ResponseEntity.status(HttpStatus.CREATED).body(created);
    }

    /** 3️⃣  List Rooms with Sensors */
    @GetMapping
    public List<RoomResponse> list() {
        return roomSvc.findAll();
    }
}
