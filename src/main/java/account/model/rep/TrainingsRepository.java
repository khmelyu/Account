package account.model.rep;

import account.model.entity.Trainings;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface TrainingsRepository extends JpaRepository<Trainings, UUID> {
}
