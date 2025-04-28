package project.financetrack.config;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpHeaders;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import project.financetrack.entities.UserEntity;
import project.financetrack.repositories.UserRepository;
import project.financetrack.security.jwt.JwtAuthenticationFilter;

/**
 * Clase de configuración de Spring Security.
 */
@Configuration
@EnableWebSecurity
@EnableMethodSecurity
@RequiredArgsConstructor
public class SecurityConfig {

    private final UserRepository userRepository;

    private final JwtAuthenticationFilter jwtAuthFilter;

    // TODO: Descomentar esto para bloquear peticiones sin token y eliminar swagger para prod
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
//        http.csrf(csrf -> csrf.disable())
//                .cors(cors -> cors.configurationSource(corsConfigurationSource()))
//                .authorizeHttpRequests(authorize -> authorize
//                        .requestMatchers("/api/v1/auth/**",
//                                "/api/v1/matchs",
//                                "/api/v1/discount/available/**",
//                                "/v3/api-docs/**",
//                                "/swagger-ui/**",
//                                "/swagger-ui/index.html",
//                                "/api/v1/**")
//                        .permitAll()
//                        .anyRequest().authenticated()
//                )
//                .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
//                .addFilterBefore(jwtAuthFilter, UsernamePasswordAuthenticationFilter.class)
//                .logout(logout ->
//                        logout.logoutUrl("/api/v1/auth/logout")
//                                .logoutSuccessHandler((request, response, authentication) -> SecurityContextHolder.clearContext())
//                );
        http.csrf(csrf -> csrf.disable())
                .cors(cors -> cors.configurationSource(corsConfigurationSource()))
                // Deshabilitar headers de seguridad de frames para h2-console
                .headers(headers -> headers.frameOptions(frameOptions -> frameOptions.disable()))
                .authorizeHttpRequests(authorize -> authorize
                        .requestMatchers("/api/v1/auth/**",
                                "/api/v1/matchs",
                                "/api/v1/discount/available/**",
                                "/v3/api-docs/**",
                                "/swagger-ui/**",
                                "/swagger-ui/index.html",
                                // Agrega la ruta a la consola H2
                                "/h2-console/**",
                                "/ping",
                                "/api/v1/**")
                        .permitAll()
                        .anyRequest().authenticated()
                )
                .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .addFilterBefore(jwtAuthFilter, UsernamePasswordAuthenticationFilter.class)
                .logout(logout ->
                        logout.logoutUrl("/api/v1/auth/logout")
                                .logoutSuccessHandler((request, response, authentication) -> SecurityContextHolder.clearContext())
                );
        return http.build();
    }

    @Bean
    public CorsConfigurationSource corsConfigurationSource() {
        CorsConfiguration configuration = new CorsConfiguration();
        configuration.addAllowedOrigin("https://financetrack-fe.vercel.app");
        configuration.addAllowedOrigin("http://localhost:4200");
        configuration.addAllowedMethod("*");
        configuration.addAllowedHeader("*");
        configuration.setAllowCredentials(true);

        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", configuration);
        return source;
    }

    private void logout(HttpServletRequest request, HttpServletResponse response, Authentication authentication) {
        String authHeader = request.getHeader(HttpHeaders.AUTHORIZATION);
        if (authHeader == null || !authHeader.startsWith("Bearer ")) {
            return;
        }

        SecurityContextHolder.clearContext();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public UserDetailsService userDetailsService() {
        return username -> {
            UserEntity user = userRepository.findByEmail(username)
                    .orElseThrow(() -> new UsernameNotFoundException("User not found"));
            return org.springframework.security.core.userdetails.User
                    .builder()
                    .username(user.getEmail())
                    .password(user.getPassword())
                    .build();
        };
    }

    @Bean
    public AuthenticationProvider authenticationProvider() {
        DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();
        authProvider.setUserDetailsService(userDetailsService());
        authProvider.setPasswordEncoder(passwordEncoder());
        return authProvider;
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration config) throws Exception {
        return config.getAuthenticationManager();
    }
}
