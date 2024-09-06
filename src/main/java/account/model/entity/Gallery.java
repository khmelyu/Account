package account.model.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity(name = "gallery")
public class Gallery {
    @Id
    private long id;
    private String phone;
    private String city;
    private String gallery;
    private String mail;
    private Long curator;
    private Long manager;
}