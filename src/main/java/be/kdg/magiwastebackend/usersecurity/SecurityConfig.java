package be.kdg.magiwastebackend.usersecurity;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfig {
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .sessionManagement().disable()
                .authorizeHttpRequests(auth -> auth.anyRequest().permitAll()) // Allow all requests
                .csrf().disable() // Optional: Disable CSRF for non-browser clients
                .formLogin().disable() // Disable the default login form
                .httpBasic().disable(); // Disable HTTP Basic authentication (optional)
        return http.build();
    }
}