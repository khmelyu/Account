package account.model.rep;

import account.model.entity.Gallery;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface GalleryRepository extends JpaRepository <Gallery, Long> {
    List<Gallery> findByCity(String city);

    List<Gallery> findByCityAndGallery(String city, String gallery);

}
