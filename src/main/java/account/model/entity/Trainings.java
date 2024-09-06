package account.model.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Getter;

import java.sql.Date;
import java.util.UUID;

@Getter
@Entity(name = "trainings")
public class Trainings {
    @Id
    private UUID id;
    private Date date;
    private Boolean actual;
}