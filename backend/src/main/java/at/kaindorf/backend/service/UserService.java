package at.kaindorf.backend.service;

import at.kaindorf.backend.dto.LoginRequestDTO;
import at.kaindorf.backend.dto.UserDTO;
import at.kaindorf.backend.exceptions.UserNotFoundException;
import at.kaindorf.backend.mapper.UserMapper;
import at.kaindorf.backend.model.User;
import at.kaindorf.backend.repositories.UserRepository;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import lombok.AllArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.access.WebInvocationPrivilegeEvaluator;
import org.springframework.stereotype.Service;

import javax.security.auth.login.LoginException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final UserMapper userMapper;
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

            Optional<User> user = Optional.ofNullable(userRepository.findUserByUsername(loginRequest.getUsername()));

            if (user.isPresent()) {
                return user.get().getId();
            }
            throw new LoginException("User not found");
        } catch (Exception e) {
            throw new LoginException(e.getMessage());
        }
    }

    public User register(String username, String password, String email, LocalDate birthdate, String firstName, String lastName) {
        if (userRepository.findUserByUsername(username) != null) {
            throw new RuntimeException("Name bereits vorhanden");
        }

        if (userRepository.findUserByEmail(email) != null) {
            throw new RuntimeException("Email bereits vorhanden");
        }

        User user = new User();
        user.setUsername(username);
        user.setPasswordhash(encoder.encode(password));
        user.setEmail(email);
        user.setBirthdate(birthdate);
        user.setFirstName(firstName);
        user.setLastName(lastName);

        User savedUser = userRepository.save(user);

        return savedUser;
    }

    public List<UserDTO> findAllUsers() {
        List<User> users = userRepository.findAll();
        return users.stream()
                .map(userMapper::toDTO)
                .toList();
    }

    public UserDTO findUserById(Long id) {
        Optional<User> user = userRepository.findById(id);

        if (user.isEmpty()) {
            throw new UserNotFoundException(id);
        }
        return userMapper.toDTO(user.get());
    }

}