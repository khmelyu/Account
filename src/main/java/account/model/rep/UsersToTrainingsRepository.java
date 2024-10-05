package account.model.rep;

import account.model.entity.Users;
import account.model.entity.UsersToTrainings;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface UsersToTrainingsRepository extends JpaRepository<UsersToTrainings, Long> {
    @Query("SELECT ut FROM users_to_trainings ut join trainings t ON ut.trainings.id = t.id WHERE ut.user = :user AND t.name LIKE '%\uD83C\uDF1F%' AND ut.actual = true AND ut.presence = true AND (ut.waiting_list = false OR ut.waiting_list IS NULL) AND ut.trainings.actual = true AND ut.trainings.date < CURRENT_DATE")
    List<UsersToTrainings> findVisitedStarTrainings(Users user);

}

