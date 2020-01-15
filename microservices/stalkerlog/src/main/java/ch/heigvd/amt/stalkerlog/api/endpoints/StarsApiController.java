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
        Long id = newStarEntity.getId();

        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest().path("/{id}")
                .buildAndExpand(newStarEntity.getId()).toUri();

        return ResponseEntity.created(location).build();
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
