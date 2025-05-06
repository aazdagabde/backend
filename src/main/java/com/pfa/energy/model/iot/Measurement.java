// src/main/java/com/pfa/energy/model/iot/Measurement.java
package com.pfa.energy.model.iot;
import jakarta.persistence.*;
import lombok.*;
import java.time.Instant;

@Entity @Getter @Setter @NoArgsConstructor
@Table(name="measurements")
public class Measurement {
    @Id @GeneratedValue(strategy=GenerationType.IDENTITY) private Long id;
    @ManyToOne(fetch=FetchType.LAZY) @JoinColumn(nullable=false) private Sensor sensor;
    private double value;
    private String unit;
    private Instant takenAt;
}
