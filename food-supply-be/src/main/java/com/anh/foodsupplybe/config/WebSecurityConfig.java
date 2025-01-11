package com.anh.foodsupplybe.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import java.util.HashMap;
import java.util.Map;


@Configuration
@EnableWebSecurity
//@EnableMethodSecurity
public class WebSecurityConfig {
    private static final String[] WHITELIST_URLS = {"/auth/**"};

    @Autowired
    private UserDetailsService userDetailsService;

    @Autowired
    private JwtAuthenticationFilter jwtAuthFilter;

    private static final Map<String, String> pathPermissionsMap = new HashMap<>();

    static {
        pathPermissionsMap.put("/products/add", "ADD_PRODUCT");
        pathPermissionsMap.put("/products/update", "UPDATE_PRODUCT");
        pathPermissionsMap.put("/products/delete", "DELETE_PRODUCT");
        pathPermissionsMap.put("/products/get-all", "GET_PRODUCT");
        pathPermissionsMap.put("/discount/", "GET_DISCOUNT");
        pathPermissionsMap.put("/discount/add", "ADD_DISCOUNT");
        pathPermissionsMap.put("/discount-history/**", "GET_HISTORY_DISCOUNT");
        pathPermissionsMap.put("/invoice/add", "ADD_INVOICE");
        pathPermissionsMap.put("/invoice/", "GET_INVOICE");
        pathPermissionsMap.put("/report/**", "GET_REPORT");
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
                .csrf(AbstractHttpConfigurer::disable)
//                .cors(AbstractHttpConfigurer::disable)
                .authorizeHttpRequests(auth -> {
                    auth.requestMatchers(WHITELIST_URLS).permitAll();
                    pathPermissionsMap.forEach((path, permissions) -> {
                        if (permissions.isEmpty()) {
                            auth.requestMatchers(path).permitAll();
                        } else {
                            auth.requestMatchers(path).hasAnyAuthority(permissions);
                        }
                    });
                    auth.anyRequest().permitAll();
                })
                .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .addFilterBefore(jwtAuthFilter, UsernamePasswordAuthenticationFilter.class);
        return http.build();
    }

    @Bean
    AuthenticationProvider authenticationProvider() {
        DaoAuthenticationProvider provider = new DaoAuthenticationProvider();
        provider.setUserDetailsService(userDetailsService);
        provider.setPasswordEncoder(passwordEncoder());
        return provider;
    }

    @Bean
    public BCryptPasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }
}
