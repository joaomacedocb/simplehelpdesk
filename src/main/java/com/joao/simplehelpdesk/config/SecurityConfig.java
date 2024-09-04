package com.joao.simplehelpdesk.config;

import java.security.interfaces.RSAPrivateKey;
import java.security.interfaces.RSAPublicKey;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.oauth2.jwt.JwtDecoder;
import org.springframework.security.oauth2.jwt.JwtEncoder;
import org.springframework.security.oauth2.jwt.NimbusJwtDecoder;
import org.springframework.security.oauth2.jwt.NimbusJwtEncoder;
import org.springframework.security.web.SecurityFilterChain;

import com.nimbusds.jose.jwk.JWKSet;
import com.nimbusds.jose.jwk.RSAKey;
import com.nimbusds.jose.jwk.source.ImmutableJWKSet;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

	@Value("${jwt.public.key}")
	private RSAPublicKey key;
	
	@Value("${jwt.private.key}")
	private RSAPrivateKey priv;
	
	@Autowired
	private UserDetailsService userDetailsService;
	
	@Bean
	SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {

		http.csrf(csrf -> csrf.disable()).authorizeHttpRequests(auth -> {
			auth.requestMatchers("/authenticate").permitAll();
			auth.anyRequest().authenticated();
		}

		).httpBasic(Customizer.withDefaults()).oauth2ResourceServer(conf -> conf.jwt(Customizer.withDefaults()));
		return http.build();

	}

	@Bean
	JwtDecoder jwtDecoder () {
		return NimbusJwtDecoder.withPublicKey(key).build();
	}
	
	@Bean
	JwtEncoder jwtEncoder () {
		var jwk = new RSAKey.Builder(key).privateKey(priv).build();
		var jwks = new ImmutableJWKSet<>(new JWKSet(jwk));
		return new NimbusJwtEncoder(jwks);
	}
	
    @SuppressWarnings("removal")
	@Bean
    AuthenticationManager authenticationManager(HttpSecurity http) throws Exception {
        return http.getSharedObject(AuthenticationManagerBuilder.class)
                   .userDetailsService(userDetailsService)
                   .passwordEncoder(bCriptPasswordEncoder()).and().build();
    }
    
	
	@Bean
	BCryptPasswordEncoder bCriptPasswordEncoder() {
		return new BCryptPasswordEncoder();
	}
	
//
//    @Autowired
//    private JWTUtil jwtUtil;
//
//    @Autowired
//    private UserDetailsService userDetailsService;
//
//    @Bean
//    SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
//    	
//        http.csrf(csrf -> csrf.disable())
//            .cors(Customizer.withDefaults());
//
//        http.addFilterBefore(new JWTAuthenticationFilter(authenticationManager(http.getSharedObject(AuthenticationConfiguration.class)), jwtUtil),
//                UsernamePasswordAuthenticationFilter.class);
//
//        http.addFilterBefore(new JWTAuthorizationFilter(authenticationManager(http.getSharedObject(AuthenticationConfiguration.class)), jwtUtil, userDetailsService),
//                UsernamePasswordAuthenticationFilter.class);
//
//        http.authorizeHttpRequests(
//                auth -> auth.requestMatchers("/login").permitAll()
//                    .anyRequest().authenticated());
//
//        http.sessionManagement(management -> management.sessionCreationPolicy(SessionCreationPolicy.STATELESS));
//
//        return http.build();
//    }

//    @Bean
//    AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration) throws Exception {
//        return authenticationConfiguration.getAuthenticationManager();
//    }
//
//    @Bean
//    CorsConfigurationSource corsConfigurationSource() {
//        CorsConfiguration configuration = new CorsConfiguration().applyPermitDefaultValues();
//        configuration.setAllowedMethods(java.util.Arrays.asList("POST", "GET", "PUT", "DELETE", "OPTIONS"));
//        final UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
//        source.registerCorsConfiguration("/**", configuration);
//        return source;
//    }

}
