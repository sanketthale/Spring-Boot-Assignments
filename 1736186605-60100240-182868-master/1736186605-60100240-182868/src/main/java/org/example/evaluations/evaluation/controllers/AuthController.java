package org.example.evaluations.evaluation.controllers;

import org.example.evaluations.evaluation.dtos.LoginRequest;
import org.example.evaluations.evaluation.dtos.LoginResponse;
import org.example.evaluations.evaluation.dtos.SignupRequest;
import org.example.evaluations.evaluation.dtos.SignupResponse;
import org.example.evaluations.evaluation.exceptions.BadCredentialsException;
import org.example.evaluations.evaluation.exceptions.UserNotSignedUpException;
import org.example.evaluations.evaluation.services.IAuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    private IAuthService authService;


    @PostMapping("/signup")
    public ResponseEntity<SignupResponse> signUp(@RequestBody SignupRequest request){

        SignupResponse response = this.authService.signup(request);

        return ResponseEntity.ok(response);

    }

    @PostMapping("/login")
    public ResponseEntity<LoginResponse> login(@RequestBody LoginRequest request) throws UserNotSignedUpException, BadCredentialsException {
        LoginResponse response = this.authService.login(request);

        return ResponseEntity.ok(response);

    }
}

