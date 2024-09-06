package account.model.dto;

import account.model.entity.Users;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@Getter
@NoArgsConstructor
public class TopUserDTO {
    private long id;
    private String name;
    private String lastname;
    private String city;
    private String gallery;
    private int trainingCount;

    public TopUserDTO(Users user, int trainingCount) {
        this.id = user.getId();
        this.name = user.getName();
        this.lastname = user.getLastname();
        this.city = user.getCity();
        this.gallery = user.getGallery();
        this.trainingCount = trainingCount;
    }

}
