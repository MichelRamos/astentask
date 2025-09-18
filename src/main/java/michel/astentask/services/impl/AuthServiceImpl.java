package michel.astentask.services.impl;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import michel.astentask.config.TokenService;
import michel.astentask.dtos.request.LoginRequest;
import michel.astentask.dtos.request.RegisterRequest;
import michel.astentask.dtos.response.AuthResponse;
import michel.astentask.dtos.response.UserResponse;
import michel.astentask.entities.User;
import michel.astentask.repositories.UsersRepository;
import michel.astentask.services.AuthService;

@Service
public class AuthServiceImpl implements AuthService{


    @Autowired
    private UsersRepository usersRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private TokenService tokenService;

    @Override
    public ResponseEntity<UserResponse> signUp(RegisterRequest payload){
        
        Optional<User> user = usersRepository.findByEmail(payload.getEmail());

        if (user.isEmpty()) {
            User newUser = new User();
            newUser.setEmail(payload.getEmail());
            newUser.setName(payload.getName());
            newUser.setPassword(passwordEncoder.encode(payload.getPassword()));

            User createdUser = usersRepository.save(newUser);

            return ResponseEntity.ok(new UserResponse(
                createdUser.getEmail(),
                createdUser.getName()
                ));
        }
        return ResponseEntity.badRequest().build();
    }

    @Override
    public ResponseEntity<AuthResponse> signIn(LoginRequest payload) {

        User user = usersRepository.findByEmail(payload.getEmail()).orElseThrow(()
            -> new RuntimeException("user not found"));

        if(passwordEncoder.matches(payload.getPassword(), user.getPassword())) {

            AuthResponse authResponse = new AuthResponse();
            
            authResponse.setToken(tokenService.generateToken(user));

            return ResponseEntity.ok(authResponse);
        }
        return ResponseEntity.badRequest().build();
    }
}
