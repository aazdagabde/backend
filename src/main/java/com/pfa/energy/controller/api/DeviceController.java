// src/main/java/com/pfa/energy/controller/api/DeviceController.java
package com.pfa.energy.controller.api;
import com.pfa.energy.dto.MeasurementDto;
import com.pfa.energy.service.MeasurementService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController @RequestMapping("/api/device") @RequiredArgsConstructor
public class DeviceController {
    private final MeasurementService svc;

    @PostMapping("/measurements")
    public ResponseEntity<Void> ingest(@RequestBody MeasurementDto dto) {
        svc.ingest(dto);
        return ResponseEntity.ok().build();
    }
}
