// src/main/java/com/pfa/energy/model/iot/Sensor.java
package com.pfa.energy.model.iot;
import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDateTime;
import java.util.UUID;

@Entity @Getter @Setter @NoArgsConstructor
@Table(name = "sensors")
public class Sensor {
    @Id private UUID id = UUID.randomUUID();

    @ManyToOne(fetch = FetchType.LAZY)                // 🔗 vers la salle
    @JoinColumn(nullable = false)
    private Room room;

    @Column(nullable = false) private String type;    // e.g. “CURRENT”, “TEMP”
    @Column(nullable = false) private String ref;     // référence matérielle
    private LocalDateTime installedAt;

    public Sensor(UUID id, Room room, String type, String ref, LocalDateTime ts) {
        this.id = id; this.room = room; this.type = type; this.ref = ref; this.installedAt = ts;
    }
}

