package com.public_services.controller.rest;

import com.public_services.controller.request.UpdateUserRequest;
import com.public_services.controller.response.UserResponse;
import com.public_services.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/users")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @GetMapping
    public ResponseEntity<Page<UserResponse>> getAll(Pageable pageable) {
        Page<UserResponse> userResponses = userService.findAll(pageable);
        return ResponseEntity.ok(userResponses);
    }

    @GetMapping("/{id}")
    public ResponseEntity<UserResponse> getById(@PathVariable Long id) {
        UserResponse userResponse = userService.findById(id);
        return ResponseEntity.ok(userResponse);
    }

    @PutMapping("/{id}")
    public ResponseEntity<HttpStatus> update(@PathVariable Long id,
                                             @RequestBody UpdateUserRequest updateUserRequest){
        userService.update(id,updateUserRequest);
        return ResponseEntity.ok(HttpStatus.OK);
    }
}
