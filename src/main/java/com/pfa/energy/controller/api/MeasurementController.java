// src/main/java/com/pfa/energy/controller/api/MeasurementController.java
package com.pfa.energy.controller.api;
import com.pfa.energy.model.iot.Measurement;
import com.pfa.energy.model.iot.Room;
import com.pfa.energy.repository.*;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController @RequestMapping("/api/measurements") @RequiredArgsConstructor
public class MeasurementController {

    private final RoomRepository roomRepo;
    private final MeasurementRepository measRepo;

    @GetMapping
    public List<Measurement> last100(@RequestParam String room) {
        Room r = roomRepo.findByName(room)
                .orElseThrow(() -> new IllegalArgumentException("Room not found"));
        return measRepo.findTop100BySensorRoomOrderByTakenAtDesc(r);
    }
}
