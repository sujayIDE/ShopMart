package in.ashokit.repository;

import in.ashokit.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User,Long> {
    public User findByUserEmail(String email);
}
