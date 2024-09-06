package account.controller;

import account.model.dto.TopUserDTO;
import account.model.dto.UserDTO;
import account.model.entity.Gallery;
import account.model.entity.Users;
import account.model.entity.UsersToTrainings;
import account.model.rep.GalleryRepository;
import account.model.rep.UserRepository;
import account.model.rep.UsersToTrainingsRepository;
import account.service.CounterService;
import account.service.DynamicGalleryService;
import account.service.SelectCuratorService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
public class UserController {
    private final UserRepository userRepository;
    private final GalleryRepository galleryRepository;
    private final UsersToTrainingsRepository usersToTrainingsRepository;
    private final DynamicGalleryService dynamicGalleryService;
    private final CounterService counterService;
    private final SelectCuratorService selectCuratorService;


    @GetMapping("/{userId}")
    public Optional<UserDTO> getUserData(@PathVariable long userId) {
        Optional<Users> userOptional = userRepository.findById(userId);

        if (userOptional.isPresent()) {
            Users user = userOptional.get();
            Long curatorId = selectCuratorService.selectCuratorToView(user);
            if (curatorId != null) {
                String curator = selectCuratorService.curatorName(curatorId);
                int visitedTrainingsCount = counterService.countVisitedTrainings(user);
                return Optional.of(new UserDTO(user, visitedTrainingsCount, curator));
            } else {
                return Optional.of(new UserDTO(user, counterService.countVisitedTrainings(user), null));
            }
        }
        return Optional.empty();
    }



    @GetMapping("/top5")
    public List<TopUserDTO> getTop5Users() {
        List<Object[]> usersWithTrainingCount = userRepository.findUsersWithTrainingCount();
        return usersWithTrainingCount.stream()
                .limit(5)
                .map(obj -> new TopUserDTO((Users) obj[0], ((Number) obj[1]).intValue()))
                .collect(Collectors.toList());
    }

    private int countVisitedTrainings(Users user) {
        List<UsersToTrainings> usersToTrainings = usersToTrainingsRepository.findByUserAndActualAndPresenceAndTrainingDateBefore(user);
        return usersToTrainings.size();
    }

    @GetMapping("/cities")
    public List<String> getAllCities() {
        return dynamicGalleryService.getAllCities();
    }

    @GetMapping("/curator")
    public List<String> getCurator() {
        return selectCuratorService.selectCurator();
    }

    @GetMapping("/gallery")
    public List<String> getGalleryOnCity(@RequestParam String city) {
        return dynamicGalleryService.getGalleryOnCity(city);
    }

    @PostMapping("/save")
    public void save(@RequestBody Map<String, Object> data) {
        Number idNumber = (Number) data.get("id");
        long id = idNumber.longValue();

        if (data.get("department") != null && data.get("department").equals("Галерея") && data.get("gallery").toString().contains(", ") && data.get("position").toString().contains("Управляющий")) {
            String[] galleryArray = data.get("gallery").toString().split(", ");

            for (String item : galleryArray) {
                List<Gallery> galleries = galleryRepository.findByCityAndGallery((String) (data.get("city")), item);
                Gallery gallery = galleries.get(0);

                gallery.setCurator(Long.valueOf((String) data.get("curator")));
                gallery.setManager(id);
                galleryRepository.save(gallery);
            }
        } else if (data.get("position") != null && data.get("position").toString().contains("Управляющий")) {
            List<Gallery> galleries = galleryRepository.findByCityAndGallery((String) (data.get("city")), (String) data.get("gallery"));
            Gallery gallery = galleries.get(0);

            gallery.setCurator(Long.valueOf((String) data.get("curator")));
            gallery.setManager(id);
            galleryRepository.save(gallery);
        }
        Users user = userRepository.findById(id).orElse(new Users());
        user.setId(id);
        user.setName((String) data.get("name"));
        user.setLastname((String) data.get("lastname"));
        user.setPhone(data.get("phone").toString().replace(" ", ""));
        user.setCity((String) data.get("city"));
        user.setDepartment((String) data.get("department"));
        user.setLevel((String) data.get("level"));
        user.setSeniority((String) data.get("seniority"));
        user.setPosition((String) data.get("position"));
        user.setGallery((String) data.get("gallery"));
        user.setDivision((String) data.get("division"));
        userRepository.save(user);
        user.setStatic_points(counterService.countPoints(user));
        userRepository.save(user);
    }
}