package at.kaindorf.backend.controller;

import at.kaindorf.backend.dto.LoginRequestDTO;
import at.kaindorf.backend.service.AccountService;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.security.auth.login.LoginException;

@RestController
@CrossOrigin(origins = "http://localhost:5173", allowCredentials = "true")
@RequestMapping("/login")
@RequiredArgsConstructor
public class LoginController {

    private final AccountService accountService;

    @PostMapping
    public ResponseEntity<Long> login(
            @RequestBody LoginRequestDTO loginRequest, HttpServletRequest request
    ) throws LoginException {
        Long userId = accountService.login(loginRequest, request);
        return ResponseEntity.ok(userId);
    }
}
