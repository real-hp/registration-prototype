package test.elfin.controller;

import org.springframework.web.bind.annotation.*;
import test.elfin.model.User;
import test.elfin.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;

import java.util.List;

@RestController
@RequestMapping(value = "user")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;


    @PostMapping(value = "/register")
    public ResponseEntity<String> register(@RequestBody User user) {
        return ResponseEntity.ok(userService.registerUser(user));
    }

    @GetMapping(value = "/all")
    public ResponseEntity<List<User>> getAllRequest() {
        return ResponseEntity.ok(userService.getAllRequest());
    }

    @ExceptionHandler(RuntimeException.class)
    public ResponseEntity handleException(RuntimeException e) {
        return ResponseEntity.badRequest().body(e.getMessage());
    }
}
