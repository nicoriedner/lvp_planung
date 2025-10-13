package at.kaindorf.backend.controller;


import at.kaindorf.backend.dto.AccountDTO;
import at.kaindorf.backend.service.AccountService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/account")
public class AccountController {
    private final AccountService accountService;

    @GetMapping("/all")
    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    public ResponseEntity<List<AccountDTO>> getAllAccounts() {
        return ResponseEntity.ok(accountService.findAllUsers());
    }

    @GetMapping("/{id}")
    public ResponseEntity<AccountDTO> getUserById(@PathVariable long id) {
        return ResponseEntity.ok(accountService.findUserById(id));
    }

    @PutMapping("/changePassword/{id}")
    public void changePassword(@PathVariable long id, @RequestBody String newPassword) {
        accountService.changePassword(id, newPassword);
    }
}
