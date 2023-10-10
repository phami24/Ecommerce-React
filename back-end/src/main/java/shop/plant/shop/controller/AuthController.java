package shop.plant.shop.controller;

import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import shop.plant.shop.dto.AuthResponseDto;
import shop.plant.shop.dto.LoginDto;
import shop.plant.shop.exception.UserException;
import shop.plant.shop.model.Users;
import shop.plant.shop.repositories.UsersRepository;
import shop.plant.shop.security.JwtProvider;
import shop.plant.shop.service.impl.CustomUserServiceImpl;

import java.time.LocalDateTime;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthController {

    private final UsersRepository userRepository;
    private final JwtProvider jwtProvider;
    private final PasswordEncoder passwordEncoder;
    private final CustomUserServiceImpl customUserService;

    // Endpoint for user registration
    @PostMapping("/user/register")
    public ResponseEntity<AuthResponseDto> createUserHandler(@RequestBody Users user) throws UserException {
        // Extract user registration information
        String email = user.getEmail();
        String password = user.getPassword();
        String firstName = user.getFirstName();
        String lastName = user.getLastName();

        // Check if the email already exists in the database
        Users isEmailExist = userRepository.findByEmail(email);

        if (isEmailExist != null) {
            throw new UserException("Email is already exists with another account");
        }

        // Create and save a new user
        Users createdUser = new Users();
        createdUser.setEmail(email);
        createdUser.setPassword(passwordEncoder.encode(password));
        createdUser.setFirstName(firstName);
        createdUser.setLastName(lastName);
        createdUser.setCreatedAt(LocalDateTime.now());
        createdUser.setRole("USER");

        Users savedUser = userRepository.save(createdUser);

        // Generate an authentication token
        Authentication authentication = new UsernamePasswordAuthenticationToken(savedUser.getEmail(), savedUser.getPassword());
        SecurityContextHolder.getContext().setAuthentication(authentication);

        String token = jwtProvider.generateToken(authentication);

        // Create an authentication response
        AuthResponseDto authResponse = new AuthResponseDto();
        authResponse.setAccessToken(token);
        authResponse.setMessage("Register Success!");

        return new ResponseEntity<>(authResponse, HttpStatus.CREATED);
    }

    // Endpoint for user login
    @PostMapping("/user/login")
    public ResponseEntity<AuthResponseDto> loginUserHandler(@RequestBody LoginDto loginDto) {
        // Extract login information
        String username = loginDto.getEmail();
        String password = loginDto.getPassword();

        // Authenticate the user
        Authentication authentication = authenticate(username, password, false);
        SecurityContextHolder.getContext().setAuthentication(authentication);

        // Generate an authentication token
        String token = jwtProvider.generateToken(authentication);

        // Create an authentication response
        AuthResponseDto authResponse = new AuthResponseDto();
        authResponse.setAccessToken(token);
        authResponse.setMessage("Login Success!");

        return new ResponseEntity<>(authResponse, HttpStatus.CREATED);
    }

    // Endpoint for admin login
    @PostMapping("/admin/login")
    public ResponseEntity<AuthResponseDto> loginAdminHandler(@RequestBody LoginDto loginDto) {
        // Extract login information
        String username = loginDto.getEmail();
        String password = loginDto.getPassword();

        // Authenticate the admin
        Authentication authentication = authenticate(username, password, true);
        SecurityContextHolder.getContext().setAuthentication(authentication);

        // Check if the user is an admin
        UserDetails userDetails = (UserDetails) authentication.getPrincipal();
        boolean isAdmin = userDetails.getAuthorities().stream()
                .anyMatch(authority -> authority.getAuthority().equals("ADMIN"));

        if (!isAdmin) {
            throw new BadCredentialsException("User is not an admin");
        }

        // Generate an authentication token
        String token = jwtProvider.generateToken(authentication);

        // Create an authentication response
        AuthResponseDto authResponse = new AuthResponseDto();
        authResponse.setAccessToken(token);
        authResponse.setMessage("Admin Login Success!");

        return new ResponseEntity<>(authResponse, HttpStatus.CREATED);
    }

    // Helper method to authenticate a user or admin
    private Authentication authenticate(String username, String password, boolean isAdmin) {
        UserDetails userDetails = customUserService.loadUserByUsername(username);

        if (userDetails == null) {
            throw new BadCredentialsException("Invalid Username");
        }

        boolean shouldEncryptPassword = !isAdmin && !password.equals(userDetails.getPassword());

        if (shouldEncryptPassword && !passwordEncoder.matches(password, userDetails.getPassword())) {
            throw new BadCredentialsException("Invalid Password");
        }

        return new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
    }
}

