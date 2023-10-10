package shop.plant.shop.security;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;

import java.util.Arrays;
import java.util.Collections;

@Configuration
public class AppConfig {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        // says no session http, commonly used in RESTfull API app, ez to expand
        http.sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .authorizeHttpRequests(authorize -> authorize
                        .requestMatchers("/api/user/**").hasRole("user")
                        .requestMatchers("/api/admin/**").hasRole("admin")
                        .requestMatchers("/api/auth/**").permitAll()
                        .requestMatchers(AUTH_WHITELIST).permitAll()
                        .anyRequest().permitAll())
                .addFilterBefore(new JwtValidator(), BasicAuthenticationFilter.class)
                .csrf(AbstractHttpConfigurer::disable)
                .cors(cors -> cors.configurationSource(new CorsConfigurationSource() {
                    @Override
                    public CorsConfiguration getCorsConfiguration(HttpServletRequest request) {
                        CorsConfiguration corsConfiguration = new CorsConfiguration();
                        corsConfiguration.setAllowedOrigins(Arrays.asList(
                                "http://localhost:3000"));

                        // allowed any method HTTP: GET POST PUT DELETE ..
                        corsConfiguration.setAllowedMethods(Collections.singletonList("*"));

                        // true: allowed browser shared credentials
                        corsConfiguration.setAllowCredentials(true);

                        // config list of headers from origin like: Content-Type, Authorization
                        corsConfiguration.setAllowedHeaders(Collections.singletonList("*"));

                        // Authorization: added to list of headers allowed
                        corsConfiguration.setExposedHeaders(Arrays.asList("Authorization"));

                        //set time of CORS request stored in cache memory of browser
                        corsConfiguration.setMaxAge(3600L);
                        return corsConfiguration;
                    }
                }))
                .formLogin(AbstractHttpConfigurer::disable)
                .httpBasic(AbstractHttpConfigurer::disable);

        return http.build();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    private static final String[] AUTH_WHITELIST = {
            "/api/v1/auth/**",
            "/v3/api-docs/**",
            "/v3/api-docs.yaml",
            "/swagger-ui/**",
            "/swagger-ui.html"
    };
}