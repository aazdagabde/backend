/* ============================================================================
 *  Security Configuration
 *  File  : src/main/java/com/pfa/energy/config/SecurityConfig.java
 * ---------------------------------------------------------------------------*/
package com.pfa.energy.config;

import com.pfa.energy.security.JwtFilter;
import com.pfa.energy.security.CustomUserDetailsService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableMethodSecurity
public class SecurityConfig {

    private final JwtFilter jwtFilter;
    private final CustomUserDetailsService userDetailsService;

    public SecurityConfig(JwtFilter jwtFilter,
                          CustomUserDetailsService userDetailsService) {
        this.jwtFilter = jwtFilter;
        this.userDetailsService = userDetailsService;
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                // Désactive CSRF car on utilise un token JWT
                .csrf(csrf -> csrf.disable())
                // Active la configuration CORS par défaut
                .cors(Customizer.withDefaults())
                // Pas de session : chaque requête doit porter son propre token
                .sessionManagement(sm -> sm.sessionCreationPolicy(SessionCreationPolicy.STATELESS))

                // Règles d'autorisation :
                //   - /api/device/**  : public (ESP, Postman, etc.)
                //   - /api/auth/**    : public (login, register, refresh)
                //   - le reste        : nécessite un JWT valide
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers("/api/device/**").permitAll()
                        .requestMatchers("/api/auth/**").permitAll()
                        .anyRequest().authenticated()
                )

                // Provider DAO + BCrypt
                .authenticationProvider(daoAuthenticationProvider())

                // Intercale le filtre JWT avant UsernamePasswordAuthenticationFilter
                .addFilterBefore(jwtFilter, UsernamePasswordAuthenticationFilter.class);

        return http.build();
    }

    /**
     * Provider d'authentification basé sur la table users (CustomUserDetailsService)
     */
    @Bean
    public DaoAuthenticationProvider daoAuthenticationProvider() {
        DaoAuthenticationProvider provider = new DaoAuthenticationProvider();
        provider.setUserDetailsService(userDetailsService);
        provider.setPasswordEncoder(passwordEncoder());
        return provider;
    }

    /**
     * Expose l'AuthenticationManager pour le service d'authentification (login)
     */
    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration authCfg) throws Exception {
        return authCfg.getAuthenticationManager();
    }

    /**
     * BCrypt, cost par défaut (10). Tu peux augmenter si besoin.
     */
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}

