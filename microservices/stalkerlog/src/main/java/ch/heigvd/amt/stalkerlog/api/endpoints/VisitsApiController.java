package ch.heigvd.amt.stalkerlog.api.endpoints;

import ch.heigvd.amt.stalkerlog.api.VisitsApi;
import ch.heigvd.amt.stalkerlog.api.model.Visit;
import ch.heigvd.amt.stalkerlog.entities.VisitEntity;
import ch.heigvd.amt.stalkerlog.repositories.CityRepository;
import ch.heigvd.amt.stalkerlog.repositories.StarRepository;
import ch.heigvd.amt.stalkerlog.repositories.VisitRepository;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
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
@Api(tags = "visits")
public class VisitsApiController implements VisitsApi {
    @Autowired
    VisitRepository visitRepository;
    @Autowired
    StarRepository starRepository;
    @Autowired
    CityRepository cityRepository;

    @Override
    public ResponseEntity<Void> postVisit(@ApiParam(value = "", required = true) @Valid Visit visit) {
        // Check if star and visit exist
        if(starRepository.findById(visit.getStarId()).isPresent() && cityRepository.findById(visit.getCityId()).isPresent()) {
            // TODO Check if user owns star
            VisitEntity newVisitEntity = toVisitEntity(visit);
            visitRepository.save(newVisitEntity);

            URI location = ServletUriComponentsBuilder
                    .fromCurrentRequest().path("/{id}")
                    .buildAndExpand(newVisitEntity.getId()).toUri();

            return ResponseEntity.created(location).build();
        } else {
            return ResponseEntity.badRequest().build();
        }
    }

    @Override
    public ResponseEntity<List<Visit>> getVisits(@Valid Integer page, @Valid Integer pageSize) {
        List<Visit> visits = new ArrayList<>();
        // TODO visits of user
        for(VisitEntity visitEntity : visitRepository.findAllByOrderByStar(PageRequest.of(page - 1, pageSize))) {
            visits.add(toVisit(visitEntity));
        }
        HttpHeaders responseHeaders = new HttpHeaders();
        responseHeaders.set("Pagination-NumberOfItems", String.valueOf(visitRepository.count()));
        responseHeaders.set("Pagination-Next", "/visits?page=" + (page + 1) + "&pageSize="+ pageSize);
        return ResponseEntity.ok()
                .headers(responseHeaders)
                .body(visits);
    }

    @Override
    public ResponseEntity<Visit> getVisit(Integer id) {
        Optional<VisitEntity> visitEntity = visitRepository.findById(Long.valueOf(id));
        if(visitEntity.isPresent()) {
            //TODO check owner
            Visit visit = toVisit(visitEntity.get());
            return ResponseEntity.ok(visit);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @Override
    public ResponseEntity<Void> patchVisit(Integer id, @Valid Visit visit) {
        VisitEntity visitEntity = toVisitEntity(visit);
        // Visit exists in database
        if(visitRepository.findById(Long.valueOf(id)).isPresent()) {
            // TODO check owner
            visitEntity.setId(id);
            visitRepository.save(visitEntity);
            return ResponseEntity.ok().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @Override
    public ResponseEntity<Void> deleteVisit(Integer id) {
        // Visit exists in database
        if(visitRepository.findById(Long.valueOf(id)).isPresent()) {
            // TODO Check owner
            visitRepository.deleteById(Long.valueOf(id));
            return ResponseEntity.status(204).build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    private VisitEntity toVisitEntity(Visit visit) {
        VisitEntity visitEntity = new VisitEntity();
        visitEntity.setStar(starRepository.findById(visit.getStarId()).get());
        visitEntity.setCity(cityRepository.findById(visit.getCityId()).get());
        visitEntity.setStartDate(visit.getStartDate());
        visitEntity.setEndDate(visit.getEndDate());
        return visitEntity;
    }

    private Visit toVisit(VisitEntity visitEntity) {
        Visit visit = new Visit();
        visit.setStarId(visitEntity.getStar().getId());
        visit.setCityId(visitEntity.getCity().getId());
        visit.setStartDate(visitEntity.getStartDate());
        visit.setEndDate(visitEntity.getEndDate());
        return visit;
    }
}
