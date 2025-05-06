// src/main/java/com/pfa/energy/model/user/User.java
package com.pfa.energy.model.user;

import jakarta.persistence.*;
import lombok.*;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import java.util.*;
import java.util.stream.Collectors;

@Entity @Getter @Setter @NoArgsConstructor
@Table(name="users")
public class User implements UserDetails {
    @Id private UUID id = UUID.randomUUID();
    @Column(unique=true,nullable=false) private String username;
    @Column(unique=true,nullable=false) private String email;
    private String nom ;
    private String prenom;
    private String telephone ;
    private String password;
    private boolean enabled=true;

    @ManyToMany(fetch=FetchType.EAGER)
    private Set<Role> roles = new HashSet<>();

    @Override
    public Collection<SimpleGrantedAuthority> getAuthorities() {
        return roles.stream()
                .map(r->new SimpleGrantedAuthority(r.getName()))
                .collect(Collectors.toList());
    }
    @Override public String getPassword(){return password;}
    @Override public String getUsername(){return username;}
    @Override public boolean isAccountNonExpired(){return true;}
    @Override public boolean isAccountNonLocked(){return true;}
    @Override public boolean isCredentialsNonExpired(){return true;}
    @Override public boolean isEnabled(){return enabled;}
}
