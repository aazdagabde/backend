// src/main/java/com/pfa/energy/repository/MeasurementRepository.java
package com.pfa.energy.repository;
import com.pfa.energy.model.iot.Measurement;
import com.pfa.energy.model.iot.Room;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface MeasurementRepository extends JpaRepository<Measurement, Long> { }
