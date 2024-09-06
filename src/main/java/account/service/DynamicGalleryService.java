package account.service;

import account.model.entity.Gallery;
import account.model.rep.GalleryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class DynamicGalleryService {

    private final GalleryRepository galleryRepository;

    @Autowired
    public DynamicGalleryService(GalleryRepository galleryRepository) {
        this.galleryRepository = galleryRepository;
    }

    public List<String> getAllCities() {
        List<Gallery> galleries = galleryRepository.findAll();

        return galleries.stream()
                .map(Gallery::getCity)
                .distinct()
                .sorted((city1, city2) -> {
                    if (city1.equals("Москва и МО")) return -1;
                    if (city2.equals("Москва и МО")) return 1;
                    if (city1.equals("Санкт-Петербург")) return -1;
                    if (city2.equals("Санкт-Петербург")) return 1;
                    return city1.compareTo(city2);
                })
                .collect(Collectors.toList());
    }


    public List<String> getGalleryOnCity(String city) {
        List<Gallery> galleries = galleryRepository.findByCity(city);
        return galleries.stream()
                .map(Gallery::getGallery)
                .sorted()
                .collect(Collectors.toList());
    }

}
