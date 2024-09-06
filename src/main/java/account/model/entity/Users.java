package account.model.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "\"user\"")
public class Users {
    @Id
    private long id;
    private String name;
    private String lastname;
    private String phone;
    private String position;
    private String department;
    private String city;
    private String gallery;
    private String level;
    private String seniority;
    private String division;
    private int static_points;
    private int dynamic_points;
}
