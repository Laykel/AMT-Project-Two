package ch.heigvd.amt.stalkerlog.api.endpoints;

import ch.heigvd.amt.stalkerlog.api.StarsApi;
import ch.heigvd.amt.stalkerlog.api.model.Star;
import ch.heigvd.amt.stalkerlog.entities.StarEntity;
import ch.heigvd.amt.stalkerlog.repositories.StarRepository;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * Spring controller to implement operations generated by codegen
 *
 * @author Alison Savary, Luc Wachter
 */
@Controller
public class StarsApiController implements StarsApi {
    @Autowired
    StarRepository starRepository;

    public ResponseEntity<Void> postStar(@ApiParam(value = "", required = true) @Valid @RequestBody Star star) {
        StarEntity newStarEntity = toStarEntity(star);
        starRepository.save(newStarEntity);

        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest().path("/{id}")
                .buildAndExpand(newStarEntity.getId()).toUri();

        return ResponseEntity.created(location).build();
    }

    public ResponseEntity<List<Star>> getStars() {
        List<Star> stars = new ArrayList<>();
        for (StarEntity starEntity : starRepository.findAll()) {
            stars.add(toStar(starEntity));
        }
        return ResponseEntity.ok(stars);
    }

    public ResponseEntity<Star> getStar(Integer id) {
        Optional<StarEntity> starEntity = starRepository.findById(Long.valueOf(id));
        if(starEntity.isPresent()) {
            // TODO check owner
            Star star = toStar(starEntity.get());
            return ResponseEntity.ok(star);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    public ResponseEntity<Void> putStar(Integer id, @Valid Star star) {
        StarEntity starEntity = toStarEntity(star);
        // Star exists in database
        if(starRepository.findById(Long.valueOf(id)).isPresent()) {
            // TODO check owner
            starEntity.setId(id);
            starRepository.save(starEntity);
            return ResponseEntity.ok().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @Override
    public ResponseEntity<Void> deleteStar(Integer id) {
        // TODO
        return null;
    }

    private StarEntity toStarEntity(Star star) {
        StarEntity entity = new StarEntity();
        entity.setName(star.getName());
        entity.setPlatform(star.getPlatform());
        return entity;
    }

    private Star toStar(StarEntity entity) {
        Star star = new Star();
        star.setName(entity.getName());
        star.setPlatform(entity.getPlatform());
        return star;
    }
}
