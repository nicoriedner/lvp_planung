package at.kaindorf.backend.controller;

import at.kaindorf.backend.dto.RegisterRequestDTO;
import at.kaindorf.backend.dto.UserDTO;
import at.kaindorf.backend.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin(origins = "http://localhost:5173", allowCredentials = "true")
@RequestMapping("/register")
@RequiredArgsConstructor
public class RegisterController {
    private final UserService userService;

    @PostMapping
    public ResponseEntity<String> register(@RequestBody RegisterRequestDTO registerDTO) {
        try {
            userService.register(registerDTO.getUsername(), registerDTO.getPassword(), registerDTO.getEmail(), registerDTO.getBirthdate(), registerDTO.getFirstName(), registerDTO.getLastName());
            return ResponseEntity.ok("User registered successfully");
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body("Error: " + e.getMessage());
        }
    }
}