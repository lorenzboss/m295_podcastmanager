package ch.bossgroup.podcastmanager.podcast;

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
public class PodcastController {

    private final PodcastService podcastService;


    public PodcastController(PodcastService podcastService) {
        this.podcastService = podcastService;
    }

    @GetMapping("/api/podcast/{id}")
    @RolesAllowed(Roles.user)
    public ResponseEntity<Podcast> getPodcast(@PathVariable Long id) {
        return new ResponseEntity<>(podcastService.getPodcast(id), HttpStatus.OK);
    }

    @GetMapping("/api/podcast")
    @RolesAllowed(Roles.user)
    public ResponseEntity<List<Podcast>> getAllPodcast() {
        return new ResponseEntity<>(podcastService.getAllPodcasts(), HttpStatus.OK);
    }

    @PostMapping("/api/podcast")
    @RolesAllowed(Roles.user)
    public ResponseEntity<Podcast> createPodcast(@RequestBody Podcast podcast) {
        return new ResponseEntity<>(podcastService.savePodcast(podcast), HttpStatus.CREATED);
    }

    @PutMapping("/api/podcast/{id}")
    @RolesAllowed(Roles.user)
    public ResponseEntity<Podcast> editPodcast(@PathVariable Long id, @RequestBody Podcast podcast) {
        return new ResponseEntity<>(podcastService.editPodcast(podcast, id), HttpStatus.OK);
    }

    @DeleteMapping("/api/podcast/{id}")
    @RolesAllowed(Roles.user)
    public ResponseEntity<Podcast> deletePodcast(@PathVariable Long id) {
        podcastService.deletePodcast(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }


}
