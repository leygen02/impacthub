package hackermen.impacthub.rest;

import hackermen.impacthub.model.OrganiserDTO;
import hackermen.impacthub.service.OrganiserService;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import jakarta.validation.Valid;
import java.util.List;
import java.util.UUID;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping(value = "/api/organisers", produces = MediaType.APPLICATION_JSON_VALUE)
public class OrganiserResource {

    private final OrganiserService organiserService;

    public OrganiserResource(final OrganiserService organiserService) {
        this.organiserService = organiserService;
    }

    @GetMapping
    public ResponseEntity<List<OrganiserDTO>> getAllOrganisers() {
        return ResponseEntity.ok(organiserService.findAll());
    }

    @GetMapping("/{organiserID}")
    public ResponseEntity<OrganiserDTO> getOrganiser(
            @PathVariable(name = "organiserID") final UUID organiserID) {
        return ResponseEntity.ok(organiserService.get(organiserID));
    }

    @PostMapping
    @ApiResponse(responseCode = "201")
    public ResponseEntity<UUID> createOrganiser(
            @RequestBody @Valid final OrganiserDTO organiserDTO) {
        return new ResponseEntity<>(organiserService.create(organiserDTO), HttpStatus.CREATED);
    }

    @PutMapping("/{organiserID}")
    public ResponseEntity<Void> updateOrganiser(
            @PathVariable(name = "organiserID") final UUID organiserID,
            @RequestBody @Valid final OrganiserDTO organiserDTO) {
        organiserService.update(organiserID, organiserDTO);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/{organiserID}")
    @ApiResponse(responseCode = "204")
    public ResponseEntity<Void> deleteOrganiser(
            @PathVariable(name = "organiserID") final UUID organiserID) {
        organiserService.delete(organiserID);
        return ResponseEntity.noContent().build();
    }

}
