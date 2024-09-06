package account.model.entity;

import jakarta.persistence.*;
import lombok.Getter;

@Getter
@Entity(name = "users_to_trainings")
public class UsersToTrainings {
    @Id
    private long id;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private Users user;

    @ManyToOne
    @JoinColumn(name = "training_id")
    private Trainings trainings;

    private boolean actual;
    private boolean presence;
    private Boolean waiting_list;

}
