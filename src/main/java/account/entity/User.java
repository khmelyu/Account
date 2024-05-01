package account.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Getter;

@Getter
@Entity(name = "\"user\"")
public class User {
    @Id
    private long id;
    private String name;
    private String lastname;
    private String phone;
    private String city;
    private String gallery;
    private String rate;
}
