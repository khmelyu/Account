package account.rep;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import account.entity.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
}
