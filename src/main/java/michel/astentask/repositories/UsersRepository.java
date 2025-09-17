package michel.astentask.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import michel.astentask.entities.User;

@Repository
public interface UsersRepository extends JpaRepository<User, Long>{

    Optional<User> findByName(String name);

    Optional<User> findByEmail(String email);
    
}
