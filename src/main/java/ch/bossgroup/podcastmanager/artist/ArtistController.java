package ch.bossgroup.podcastmanager.artist;

import ch.bossgroup.podcastmanager.podcast.Podcast;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class ArtistController {

    private final ArtistService artistService;


    public ArtistController(ArtistService artistService) {
        this.artistService = artistService;
    }

    @GetMapping("/api/podcast")
    public ResponseEntity<List<Artist>> getPodcasts() {
        return new ResponseEntity<>(artistService.getAllArtists(), HttpStatus.OK);
    }

    @PostMapping("/api/podcast")
    public ResponseEntity<Artist> createPodcast(@RequestBody Artist artist) {
        return new ResponseEntity<>(artist, HttpStatus.CREATED);
    }
}
