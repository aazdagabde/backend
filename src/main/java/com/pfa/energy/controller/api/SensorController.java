package com.pfa.energy.controller.api;

import com.pfa.energy.dto.room.SensorRequest;
import com.pfa.energy.dto.room.SensorResponse;
import com.pfa.energy.service.SensorService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/sensors") @RequiredArgsConstructor
public class SensorController {

    private final SensorService sensorSvc;

    /** 2️⃣  Add Sensor + liaison à une Room */
    @PostMapping
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<SensorResponse> add(@Valid @RequestBody SensorRequest req) {
        SensorResponse created = sensorSvc.add(req);
        return ResponseEntity.status(HttpStatus.CREATED).body(created);
    }
}
