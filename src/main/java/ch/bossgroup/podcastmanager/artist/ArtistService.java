package ch.bossgroup.podcastmanager.artist;

import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ArtistService {

    private final ArtistRepository repository;

    public ArtistService(ArtistRepository repository) {
        this.repository = repository;
    }

    public List<Artist> getAllArtists() {
        return repository.findAll();
    }

    public Artist getArtist(Long id) {
        return repository.findById(id).orElse(null);
    }

    public Artist saveArtist(Artist artist) {
        return repository.save(artist);
    }

    public Artist updateArtist(Artist artist, Long id) {
        return repository.findById(id)
                .map(originalArtist -> {
                    originalArtist.setId(id);
                    originalArtist.setFirstname(artist.getFirstname());
                    originalArtist.setLastname(artist.getLastname());
                    return repository.save(originalArtist);
                })
                .orElse(null);
    }

    public void deleteArtist(Long id) {
        repository.deleteById(id);
    }


}
