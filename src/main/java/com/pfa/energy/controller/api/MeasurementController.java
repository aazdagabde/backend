// src/main/java/com/pfa/energy/controller/api/MeasurementController.java
package com.pfa.energy.controller.api;
import com.pfa.energy.dto.iot.MeasurementRequest;
import com.pfa.energy.dto.iot.MeasurementResponse;
import com.pfa.energy.model.iot.Measurement;
import com.pfa.energy.model.iot.Room;
import com.pfa.energy.repository.*;
import com.pfa.energy.service.MeasurementService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController @RequestMapping("/api/measurements") @RequiredArgsConstructor
public class MeasurementController {

    private final MeasurementService measurementSvc;

    /** 4️⃣  Ingestion de mesures depuis Arduino/ESP */
    @PostMapping
    // @PreAuthorize("hasRole('DEVICE')")  // optionnel : voir §2 Sécurité
    public ResponseEntity<MeasurementResponse> ingest(@Valid @RequestBody MeasurementRequest req) {
        var res = measurementSvc.save(req);
        return ResponseEntity.status(HttpStatus.CREATED).body(res);
    }
}

