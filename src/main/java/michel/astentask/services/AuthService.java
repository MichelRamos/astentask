package michel.astentask.services;

import org.springframework.http.ResponseEntity;

import michel.astentask.dtos.request.LoginRequest;
import michel.astentask.dtos.request.RegisterRequest;
import michel.astentask.dtos.response.AuthResponse;
import michel.astentask.dtos.response.UserResponse;

public interface AuthService {

    ResponseEntity<UserResponse> signUp(RegisterRequest payload);

    ResponseEntity<AuthResponse> signIn(LoginRequest payload);
    
}
