package account.model.dto;

import account.model.entity.Users;
import lombok.Getter;
import org.springframework.stereotype.Component;

@Component
@Getter
public class UserDTO {
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
    private int points;
    private String curator;
    private int visitedTrainingsCount;


    public UserDTO() {
    }
    public UserDTO(Users user, int visitedTrainingsCount, String curator) {
        this.id = user.getId();
            this.name = user.getName();
            this.lastname = user.getLastname();
            this.phone = user.getPhone();
            this.position = user.getPosition();
            this.department = user.getDepartment();
            this.city = user.getCity();
            this.gallery = user.getGallery();
            this.level = user.getLevel();
            this.seniority = user.getSeniority();
            this.division = user.getDivision();
            this.points = user.getStatic_points() + user.getDynamic_points();
            this.visitedTrainingsCount = visitedTrainingsCount;
            this.curator = curator;
    }
}