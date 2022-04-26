package me.dio.votacao.bbb.api.configuration;

import me.dio.votacao.bbb.api.security.JWTUtil;
import me.dio.votacao.bbb.api.security.JwtAuthenticationFilter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import java.util.Arrays;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    private UserDetailsService userDetailsService;
    private final JWTUtil jwtUtil;
    private final Environment environment;

    private static final String[]  PUBLIC_MATCHERS = {
            "/h2-console/**",
            "/v2/api-docs/**",
            "/swagger/**"
    };

    private static final String[]  PUBLIC_MATCHERS_GET = {
            "/api/v1/participant/**",
            "/api/v1/status/**",
            "/api/v1/user/**",
    };

    private static final String[]  PUBLIC_MATCHERS_POST = {
        "/api/v1/user/**",
    };

    private static final String[]  PUBLIC_MATCHERS_PUT = {

    };

    public SecurityConfig(UserDetailsService userDetailsService, JWTUtil jwtUtil, Environment environment) {
        this.userDetailsService = userDetailsService;
        this.jwtUtil = jwtUtil;
        this.environment = environment;
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {

        if(Arrays.asList(environment.getActiveProfiles()).contains("test")) {
            http
                    .headers()
                    .frameOptions()
                    .disable();
        }

        http
                .cors()
                .and()
                .csrf().disable();

        http
                .authorizeRequests()
                .antMatchers(HttpMethod.POST,PUBLIC_MATCHERS_POST).permitAll()
                .antMatchers(HttpMethod.GET, PUBLIC_MATCHERS_GET).permitAll()
                .antMatchers(PUBLIC_MATCHERS).permitAll()
                .anyRequest()
                .authenticated();

        http
                .addFilter(new JwtAuthenticationFilter(authenticationManager(), jwtUtil));

        http
                .sessionManagement()
                .sessionCreationPolicy(
                        SessionCreationPolicy.STATELESS
                );

    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService).passwordEncoder(bCryptPasswordEncoder());
    }

    @Bean
    CorsConfigurationSource corsConfigurationSource() {

        CorsConfiguration configuration = new CorsConfiguration().applyPermitDefaultValues();

        configuration.setAllowedMethods(Arrays.asList(
                "POST",
                "GET",
                "PUT",
                "DELETE",
                "OPTIONS"
        ));

        final UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", configuration);
        return source;
    }

    @Bean
    public BCryptPasswordEncoder bCryptPasswordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
