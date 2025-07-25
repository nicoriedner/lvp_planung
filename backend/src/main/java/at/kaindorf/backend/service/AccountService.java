package at.kaindorf.backend.service;

import at.kaindorf.backend.dto.LoginRequestDTO;
import at.kaindorf.backend.dto.AccountDTO;
import at.kaindorf.backend.exceptions.EmailAlreadyUsedException;
import at.kaindorf.backend.exceptions.UserAlreadyExistsException;
import at.kaindorf.backend.exceptions.UserNotFoundException;
import at.kaindorf.backend.mapper.AccountMapper;
import at.kaindorf.backend.model.Account;
import at.kaindorf.backend.model.Role;
import at.kaindorf.backend.repositories.AccountRepository;
import at.kaindorf.backend.security.JwtUtil;
import lombok.AllArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.security.auth.login.LoginException;
import java.time.LocalDate;
import java.util.Collections;
import java.util.List;

@Service
@AllArgsConstructor
public class AccountService {

    private final AccountRepository accountRepository;
    private final AccountMapper accountMapper;
    private final PasswordEncoder encoder;

    private final AuthenticationManager authenticationManager;
    private final JwtUtil jwtUtil;

    public String login(LoginRequestDTO loginRequest) throws LoginException {
        try {
            Authentication authentication = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(
                            loginRequest.getUsername(),
                            loginRequest.getPassword()
                    )
            );

            Account account = accountRepository.findByUsername(loginRequest.getUsername())
                    .orElseThrow(() -> new UsernameNotFoundException("Benutzer nicht gefunden: " + loginRequest.getUsername()));

            return jwtUtil.generateToken(account);

        } catch (Exception e) {
            throw new LoginException("Login fehlgeschlagen: " + e.getMessage());
        }
    }

    public Account register(String username, String password, String email, LocalDate birthdate, String firstName, String lastName) {
        if (accountRepository.findByUsername(username).isPresent()) {
            throw new UserAlreadyExistsException(username);
        }

        if (accountRepository.findByEmail(email).isPresent()) {
            throw new EmailAlreadyUsedException();
        }

        Account account = new Account();
        account.setUsername(username);
        account.setPasswordhash(encoder.encode(password));
        account.setEmail(email);
        account.setBirthdate(birthdate);
        account.setFirstName(firstName);
        account.setLastName(lastName);
        account.setRoles(Collections.singleton(Role.ROLE_USER));

        return accountRepository.save(account);
    }

    public List<AccountDTO> findAllUsers() {
        List<Account> accounts = accountRepository.findAll();
        return accounts.stream()
                .map(accountMapper::toDTO)
                .toList();
    }

    public AccountDTO findUserById(Long id) {
        Account account = accountRepository.findById(id)
                .orElseThrow(() -> new UserNotFoundException(id));

        return accountMapper.toDTO(account);
    }

    public void changePassword(Long id, String newPassword) {
        Account account = accountRepository.findById(id)
                .orElseThrow(() -> new UserNotFoundException(id));

        account.setPasswordhash(encoder.encode(newPassword));
        accountRepository.save(account);
    }

}
