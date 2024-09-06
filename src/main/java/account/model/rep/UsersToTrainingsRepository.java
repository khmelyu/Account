package account.model.rep;

import account.model.entity.Users;
import account.model.entity.UsersToTrainings;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.sql.Date;
import java.util.List;

public interface UsersToTrainingsRepository extends JpaRepository<UsersToTrainings, Long> {
    @Query("SELECT ut FROM users_to_trainings ut " +
            "WHERE ut.user = :user " +
            "AND ut.actual = true " +
            "AND ut.presence = true " +
            "AND (ut.waiting_list = false OR ut.waiting_list IS NULL) " +
            "AND ut.trainings.actual = true " +
            "AND ut.trainings.date < CURRENT_DATE")
    List<UsersToTrainings> findByUserAndActualAndPresenceAndTrainingDateBefore(Users user);

}

