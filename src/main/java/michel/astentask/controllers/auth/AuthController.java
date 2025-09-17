package michel.astentask.controllers.auth;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import jakarta.validation.Valid;
import michel.astentask.dtos.request.LoginRequest;
import michel.astentask.dtos.request.RegisterRequest;
import michel.astentask.dtos.response.AuthResponse;
import michel.astentask.dtos.response.UserResponse;
import michel.astentask.services.AuthService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;


@RestController
@RequestMapping("/api/auth")
public class AuthController {

    @Autowired
    AuthService authService;

    @PostMapping("/register")
    public ResponseEntity<UserResponse> signUp(@RequestBody @Valid RegisterRequest payload) {
        
        ResponseEntity<UserResponse> userResponse = authService.signUp(payload);

        return userResponse;
    }

    @PostMapping("/login")
    public ResponseEntity<AuthResponse> signIn(@RequestBody @Valid LoginRequest payload) {
        
        ResponseEntity<AuthResponse> authResponse = authService.signIn(payload);

        return authResponse;
    }
    
    
}
