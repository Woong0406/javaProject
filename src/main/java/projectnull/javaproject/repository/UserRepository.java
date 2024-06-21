package projectnull.javaproject.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import projectnull.javaproject.entity.User;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByUsername(String username);
}
