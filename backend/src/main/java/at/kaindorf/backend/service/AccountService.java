package at.kaindorf.backend.service;

import at.kaindorf.backend.dto.LoginRequestDTO;
import at.kaindorf.backend.dto.AccountDTO;
import at.kaindorf.backend.exceptions.EmailAlreadyUsedException;
import at.kaindorf.backend.exceptions.UserAlreadyExistsException;
import at.kaindorf.backend.exceptions.UserNotFoundException;
import at.kaindorf.backend.mapper.AccountMapper;
import at.kaindorf.backend.repositories.AccountRepository;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import lombok.AllArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.access.WebInvocationPrivilegeEvaluator;
import org.springframework.stereotype.Service;

import javax.security.auth.login.LoginException;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class AccountService {

    private final AccountRepository accountRepository;
    private final AccountMapper accountMapper;
    private final WebInvocationPrivilegeEvaluator privilegeEvaluator;
    private final BCryptPasswordEncoder encoder;

    private final AuthenticationManager authenticationManager;

    public Long login(LoginRequestDTO loginRequest, HttpServletRequest request) throws LoginException {
        try {
            Authentication authenticationRequest =
                    UsernamePasswordAuthenticationToken.unauthenticated(loginRequest.getUsername(), loginRequest.getPassword());
            Authentication authenticationResponse = this.authenticationManager.authenticate(authenticationRequest);

            SecurityContextHolder.getContext().setAuthentication(authenticationResponse);

            HttpSession session = request.getSession();
            session.setAttribute("SPRING_SECURITY_CONTEXT", SecurityContextHolder.getContext());

            Optional<at.kaindorf.backend.model.Account> user = accountRepository.findByUsername(loginRequest.getUsername());

            if (user.isPresent()) {
                return user.get().getId();
            }
            throw new UserNotFoundException(user.get().getId());
        } catch (Exception e) {
            throw new LoginException(e.getMessage());
        }
    }

    public at.kaindorf.backend.model.Account register(String username, String password, String email, LocalDate birthdate, String firstName, String lastName) {
        if (accountRepository.findByUsername(username) != null) {
            throw new UserAlreadyExistsException(username);
        }

        if (accountRepository.findByEmail(email) != null) {
            throw new EmailAlreadyUsedException();
        }

        at.kaindorf.backend.model.Account account = new at.kaindorf.backend.model.Account();
        account.setUsername(username);
        account.setPasswordhash(encoder.encode(password));
        account.setEmail(email);
        account.setBirthdate(birthdate);
        account.setFirstName(firstName);
        account.setLastName(lastName);

        at.kaindorf.backend.model.Account savedAccount = accountRepository.save(account);

        return savedAccount;
    }

    public List<AccountDTO> findAllUsers() {
        List<at.kaindorf.backend.model.Account> accounts = accountRepository.findAll();
        return accounts.stream()
                .map(accountMapper::toDTO)
                .toList();
    }

    public AccountDTO findUserById(Long id) {
        at.kaindorf.backend.model.Account account = accountRepository.findById(id)
                .orElseThrow(() -> new UserNotFoundException(id));

        return accountMapper.toDTO(account);
    }

    public void changePassword(Long id, String newPassword) {
        at.kaindorf.backend.model.Account account = accountRepository.findById(id)
                .orElseThrow(() -> new UserNotFoundException(id));

        account.setPasswordhash(encoder.encode(newPassword));
        accountRepository.save(account);
    }

}
