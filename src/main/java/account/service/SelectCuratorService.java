package account.service;

import account.model.entity.Users;
import account.model.rep.GalleryRepository;
import account.model.rep.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class SelectCuratorService {
    private final GalleryRepository galleryRepository;
    private final UserRepository userRepository;
    public List<String> selectCurator() {
        return userRepository.findCurator();
    }

    public Long selectCuratorToView(Users users) {
        String gallery = users.getGallery();

        if (gallery == null) {
            return null;
        }

        if (gallery.contains(",")) {
            gallery = gallery.split(",")[0].trim();
        }

        return userRepository.findCuratorId(users.getCity(), gallery);
    }



    public String curatorName(long id) {
        return userRepository.findCuratorById(id);
    }

}
