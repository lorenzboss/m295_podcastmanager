package ch.bossgroup.podcastmanager.artist;

import ch.bossgroup.podcastmanager.security.Roles;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.annotation.security.RolesAllowed;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@SecurityRequirement(name = "bearerAuth")
@Validated
public class ArtistController {

    private final ArtistService artistService;


    public ArtistController(ArtistService artistService) {
        this.artistService = artistService;
    }

    @GetMapping("/api/artist/{id}")
    @RolesAllowed(Roles.user)
    public ResponseEntity<Artist> getArtist(@PathVariable Long id) {
        return new ResponseEntity<>(artistService.getArtist(id), HttpStatus.OK);
    }

    @GetMapping("/api/artist")
    @RolesAllowed(Roles.user)
    public ResponseEntity<List<Artist>> getAllArtist() {
        return new ResponseEntity<>(artistService.getAllArtists(), HttpStatus.OK);
    }

    @PostMapping("/api/artist")
    @RolesAllowed(Roles.staff)
    public ResponseEntity<Artist> createArtist(@RequestBody Artist artist) {
        return new ResponseEntity<>(artistService.saveArtist(artist), HttpStatus.CREATED);
    }

    @PutMapping("/api/artist/{id}")
    @RolesAllowed(Roles.staff)
    public ResponseEntity<Artist> editArtist(@PathVariable Long id, @RequestBody Artist artist) {
        return new ResponseEntity<>(artistService.editArtist(artist, id), HttpStatus.OK);
    }

    @DeleteMapping("/api/artist/{id}")
    @RolesAllowed(Roles.admin)
    public ResponseEntity<Artist> deleteArtist(@PathVariable Long id) {
        artistService.deleteArtist(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }


}
