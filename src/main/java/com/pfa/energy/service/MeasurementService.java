package com.pfa.energy.service;

import com.pfa.energy.dto.iot.MeasurementRequest;
import com.pfa.energy.dto.iot.MeasurementResponse;
import com.pfa.energy.model.iot.Measurement;
import com.pfa.energy.model.iot.Sensor;
import com.pfa.energy.repository.MeasurementRepository;
import com.pfa.energy.repository.SensorRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.Instant;

@Service
@RequiredArgsConstructor
public class MeasurementService {

    private final MeasurementRepository measRepo;
    private final SensorRepository sensorRepo;

    @Transactional
    public MeasurementResponse save(MeasurementRequest req) {

        // Cherche le capteur par UUID
        Sensor sensor = sensorRepo.findById(req.sensorId())
                .orElseThrow(() -> new EntityNotFoundException(
                        "Capteur %s introuvable".formatted(req.sensorId())));

        // Crée la mesure
        Measurement m = new Measurement();
        m.setSensor(sensor);
        m.setValue(req.value());
        m.setUnit(req.unit());
        m.setTakenAt(Instant.now());

        measRepo.save(m);

        // Mapper la réponse
        return new MeasurementResponse(
                m.getId(),
                sensor.getId(),
                m.getValue(),
                m.getUnit(),
                m.getTakenAt()
        );
    }
}