package me.dio.votacao.bbb.api.controller;

import me.dio.votacao.bbb.api.security.JWTUtil;
import me.dio.votacao.bbb.api.security.UserSecurity;
import me.dio.votacao.bbb.api.service.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;

@RestController
@RequestMapping("/api/v1/auth")
public class AuthController {

    private final JWTUtil jwtUtil;

    public AuthController(JWTUtil jwtUtil) {
        this.jwtUtil = jwtUtil;
    }

    @PostMapping(value = "/refresh_token")
    public ResponseEntity<Void> refreshToken(HttpServletResponse response) {
        UserSecurity user = UserService.authenticated();

        String token = jwtUtil.generateToken(user.getUsername());
        response.addHeader("Authorization", "Bearer " + token);
        response.addHeader("access-control-expose-headers", "Authorization");
        return ResponseEntity.noContent().build();
    }
}
