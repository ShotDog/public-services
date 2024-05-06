package com.public_services.controller.rest;

import com.public_services.controller.request.CreateLoginRequest;
import com.public_services.controller.request.LoginRequest;
import com.public_services.controller.request.UpdateLoginRequest;
import com.public_services.controller.response.LoginResponse;
import com.public_services.service.LoginService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/logins")
@RequiredArgsConstructor
public class LoginController {

    private final LoginService loginService;

    @PostMapping
    public ResponseEntity<Long> create(@RequestBody CreateLoginRequest user) {
        Long id = loginService.create(user);
        return new ResponseEntity<>(id, HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<Page<LoginResponse>> getAll(Pageable pageable) {
        Page<LoginResponse> users = loginService.findAll(pageable);
        return new ResponseEntity<>(users, HttpStatus.OK);
    }

    @GetMapping("/auth")
    public ResponseEntity<String> login(LoginRequest loginRequest) {
        String role = loginService.login(loginRequest);
        return new ResponseEntity<>(role, HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<HttpStatus> update(@PathVariable Long id, @RequestBody UpdateLoginRequest user) {
        loginService.update(id, user);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<HttpStatus> delete(@PathVariable Long id) {
        loginService.delete(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

}
