// src/main/java/com/pfa/energy/model/iot/Room.java
package com.pfa.energy.model.iot;
import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Entity @Getter @Setter @NoArgsConstructor
@Table(name = "rooms")
public class Room {
    @Id private UUID id = UUID.randomUUID();

    @Column(unique = true, nullable = false)
    private String name;

    /** ①  Association bidirectionnelle (optionnelle mais pratique : GET /api/rooms renverra les capteurs) */
    @OneToMany(mappedBy = "room", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
    private List<Sensor> sensors = new ArrayList<>();

    public Room(UUID id, String name) {
        this.id = id;
        this.name = name;
    }
}

