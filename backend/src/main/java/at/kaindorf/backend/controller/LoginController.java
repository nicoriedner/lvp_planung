package at.kaindorf.backend.controller;

import at.kaindorf.backend.dto.LoginRequestDTO;
import at.kaindorf.backend.service.AccountService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.security.auth.login.LoginException;
import java.util.Map;

@RestController
@CrossOrigin(origins = "http://localhost:5173", allowCredentials = "true")
@RequestMapping("/login")
@RequiredArgsConstructor
public class LoginController {

    private final AccountService accountService;

    @PostMapping("/login")
    public ResponseEntity<Map<String, String>> login(@RequestBody LoginRequestDTO loginRequest) throws LoginException {
        String token = accountService.login(loginRequest);
        return ResponseEntity.ok(Map.of("token", token));
    }
}
