package account.model.rep;

import account.model.entity.Users;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface UserRepository extends JpaRepository<Users, Long> {

    @Query("SELECT u, COUNT(ut.id) AS trainingCount " +
            "FROM Users u " +
            "JOIN users_to_trainings ut ON u.id = ut.user.id " +
            "WHERE ut.actual = true " +
            "AND ut.presence = true " +
            "AND (ut.waiting_list = false OR ut.waiting_list IS NULL) " +
            "AND ut.trainings.actual = true " +
            "AND ut.trainings.date < CURRENT_DATE " +
            "GROUP BY u.id " +
            "ORDER BY trainingCount DESC")
    List<Object[]> findUsersWithTrainingCount();
    @Query("SELECT CONCAT( id, '?', name, ' ', lastname) FROM Users where division = 'Куратор'")
    List<String> findCurator();

    @Query("SELECT curator from gallery where city = :city and gallery = :gallery")
    Long findCuratorId(@Param("city") String city, @Param("gallery") String gallery);

    @Query("SELECT CONCAT(name, ' ', lastname) FROM Users where id = :id")
    String findCuratorById(@Param("id") long id);
}
