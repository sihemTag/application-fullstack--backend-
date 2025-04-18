package com.example.mdd.controllers;


import com.example.mdd.configuration.TokenProvider;
import com.example.mdd.dto.RegisterDTO;
import com.example.mdd.exceptions.UserAlreadyExistException;
import com.example.mdd.models.UserEntity;
import com.example.mdd.services.IUserService;
import com.example.mdd.services.UserService;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.Optional;

@RestController
public class UserController {

    private final UserService userService;
    private final IUserService iUserService;
    private final AuthenticationManager authenticationManager;
    private final TokenProvider tokenProvider;
    private static final String AUTHORIZATION_HEADER = "Authorization";

    public UserController(UserService userService, IUserService iUserService, AuthenticationManager authenticationManager, TokenProvider tokenProvider) {
        this.userService = userService;
        this.iUserService = iUserService;
        this.authenticationManager = authenticationManager;
        this.tokenProvider = tokenProvider;
    }

    @PostMapping("/api/auth/register")
    public ResponseEntity<?> register(@RequestBody RegisterDTO registerDto) {
        try {
            iUserService.save(registerDto);

            UsernamePasswordAuthenticationToken authenticationToken =
                    new UsernamePasswordAuthenticationToken(registerDto.getEmail(), registerDto.getPassword());

            Authentication authentication = authenticationManager.authenticate(authenticationToken);
            SecurityContextHolder.getContext().setAuthentication(authentication);

            String jwt = tokenProvider.createToken(authentication);
            HttpHeaders httpHeaders = new HttpHeaders();
            httpHeaders.add(AUTHORIZATION_HEADER, "Bearer " + jwt);

            return new ResponseEntity<>(new JWTToken(jwt), httpHeaders, HttpStatus.OK);
        } catch (UserAlreadyExistException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(Collections.emptyMap());
        }
    }

    @PostMapping("/api/auth/login")
    public ResponseEntity<?> authorize(@RequestBody LoginRequest loginDTO) {
        try {
            UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(
                    loginDTO.getEmail(), loginDTO.getPassword());

            Authentication authentication = authenticationManager.authenticate(authenticationToken);
            SecurityContextHolder.getContext().setAuthentication(authentication);

            String jwt = tokenProvider.createToken(authentication);
            HttpHeaders httpHeaders = new HttpHeaders();
            httpHeaders.add(AUTHORIZATION_HEADER, "Bearer " + jwt);

            return new ResponseEntity<>(Collections.singletonMap("token", jwt), httpHeaders, HttpStatus.OK);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(Collections.emptyMap());
        }
    }

    @GetMapping("/api/auth/me")
    public Optional<UserEntity> currentUserName(Authentication authentication) {
        return userService.currentUserName(authentication);
    }

    @GetMapping("/api/user/{id}")
    public Optional<UserEntity> getUserById(@PathVariable Integer id){
        return userService.getUserById(id);
    }


    static class JWTToken {

        private String idToken;

        public JWTToken(String idToken) {
            this.idToken = idToken;
        }

        @JsonProperty("token")
        String getIdToken() {
            return idToken;
        }

        void setIdToken(String idToken) {
            this.idToken = idToken;
        }
    }


}
