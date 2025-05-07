// src/main/java/com/pfa/energy/controller/api/DeviceController.java
package com.pfa.energy.controller.api;

import com.pfa.energy.dto.iot.MeasurementRequest;
import com.pfa.energy.dto.iot.MeasurementResponse;
import com.pfa.energy.service.MeasurementService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
public class DeviceController {

    private final MeasurementService measurementService;

    @PostMapping("/api/device/measurements")
    @ResponseStatus(HttpStatus.CREATED)
    public MeasurementResponse ingest(@Valid @RequestBody MeasurementRequest request) {
        return measurementService.save(request);
    }
}
