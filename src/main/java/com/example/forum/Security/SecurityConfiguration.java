package com.example.forum.Security;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class SecurityConfiguration implements WebMvcConfigurer{

    // @Override
    // public void addCorsMappings(CorsRegistry registry) {
    //     // Allow cross-origin requests from a specific origin (for example, your frontend running on localhost:3000)
    //     registry.addMapping("/**") // Allow all endpoints to accept cross-origin requests
    //             .allowedOrigins("http://localhost:3000") // Add your frontend URL here
    //             .allowedMethods("GET", "POST", "PUT", "DELETE") // Allow the HTTP methods you need
    //             .allowedHeaders("*") // Allow all headers
    //             .allowCredentials(true); // Allow cookies and credentials if needed
    // }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http.csrf(csrf -> csrf.disable()) // For test purposes
            .authorizeHttpRequests((requests) -> requests
				.requestMatchers("/user/addUser", "/user/authUser").permitAll()
				.anyRequest().authenticated()
			).httpBasic(Customizer.withDefaults());
        return http.build();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder(); // Password encoder for user passwords
    }
}