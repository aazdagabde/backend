// src/main/java/com/pfa/energy/model/user/Role.java
package com.pfa.energy.model.user;
import jakarta.persistence.*;
import lombok.*;

@Entity @Getter @Setter @NoArgsConstructor
@Table(name="roles")
public class Role {
    @Id @GeneratedValue(strategy=GenerationType.IDENTITY) private Integer id;
    @Column(unique=true,nullable=false) private String name;
    public Role(Integer id,String name){this.id=id;this.name=name;}
}
