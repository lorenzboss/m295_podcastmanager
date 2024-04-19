package ch.bossgroup.podcastmanager.artist;

import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;

@Service
public class ArtistService {

    private final ArtistRepository repository;

    public ArtistService(ArtistRepository repository) {
        this.repository = repository;
    }

    public List<Artist> getAllArtists() {
        return repository.findByOrderById();
    }

    public Artist getArtist(Long id) {
        return repository.findById(id).orElse(null);
    }

    public Artist saveArtist(Artist artist) {
        return repository.save(artist);
    }

    public Artist editArtist(Artist artist, Long id) {
        if (repository.existsById(id)) {
            Artist newArtist = new Artist(id, artist.getFirstname(), artist.getLastname());
            return repository.save(newArtist);
        } else throw new NoSuchElementException("Object with Id: " + id + " does not exist!");
    }

    public void deleteArtist(Long id) {
        if (repository.existsById(id)) {
            repository.deleteById(id);
        } else throw new NoSuchElementException("Object with Id: " + id + " does not exist!");
    }
}
