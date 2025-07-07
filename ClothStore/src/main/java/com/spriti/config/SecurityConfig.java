package com.spriti.config;

import jakarta.servlet.http.HttpServletRequest;
import org.apache.tomcat.util.file.ConfigurationSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.HttpSecurityDsl;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;

import java.net.http.HttpRequest;
import java.util.Arrays;
import java.util.Collections;

@Configuration
public class SecurityConfig {

    @Bean
    public SecurityFilterChain securityConfiguration(HttpSecurity http) throws Exception {
        http
                .cors(cors ->
                        cors.configurationSource(
                                new CorsConfigurationSource() {
                                    @Override
                                    public CorsConfiguration getCorsConfiguration(HttpServletRequest request) {
                                        CorsConfiguration cfg = new CorsConfiguration();

                                        cfg.setAllowedOriginPatterns(Collections.singletonList("*"));
                                        cfg.setAllowedMethods(Collections.singletonList("*"));
                                        cfg.setAllowCredentials(true);
                                        cfg.setAllowedHeaders(Collections.singletonList("*"));
                                        cfg.setExposedHeaders(Arrays.asList("Authorization"));
                                        return cfg;
                                    }
                                }
                        )
                )
                .authorizeHttpRequests(auth -> {
                    auth
                            //Accessible to all
                            .requestMatchers("/swagger-ui/**", "/v3/api-docs/**", "/newPerson", "/signIn").permitAll()

                            //Accessible to Only ADMIN or USER
                            .requestMatchers("/product/{id}", "/prod/{category}", "/showAll").hasAnyRole("ADMIN", "USER")

                            //ADMIN Endpoints
                            .requestMatchers("/admin/allUsers/{role}").hasRole("ADMIN")
                            .requestMatchers("/admin/saveProduct", "/admin/updatePrice/{id}",
                                    "/admin/updateStock/{id}", "/admin/delete/{id}").hasRole("ADMIN")

                            //USER Endpoints
                            .requestMatchers("/user/addToCart", "/user/removeFromCart").hasRole("USER")
                            .requestMatchers("/user/placeOrder/{userId}", "/user/cancelOrder/{orderId}",
                                    "/user/orderList/{userId}").hasRole("USER")

                            .anyRequest().authenticated();

                })
                .csrf(csrf -> csrf.disable())
                .formLogin(Customizer.withDefaults())
                .httpBasic(Customizer.withDefaults())
                .logout(logout -> logout
                        .logoutUrl("/logout")
                        .logoutSuccessUrl("/login")
                        .deleteCookies("JSESSIONID")
                );

        return http.build();

    }

    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }
}
