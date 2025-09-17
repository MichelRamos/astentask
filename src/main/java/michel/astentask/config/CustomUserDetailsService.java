package michel.astentask.config;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import michel.astentask.entities.User;
import michel.astentask.repositories.UsersRepository;

@Component
public class CustomUserDetailsService implements UserDetailsService{

    @Autowired
    private UsersRepository usersRepository;

    @Override
    public UserDetails loadUserByUsername(String name) throws UsernameNotFoundException {
        User user = usersRepository.findByName(name)
            .orElseThrow(() -> new UsernameNotFoundException("User not found with username: " + name));
        return new org.springframework.security.core.userdetails.User(
            user.getName(), user.getPassword(), new ArrayList<>());
        };
}
