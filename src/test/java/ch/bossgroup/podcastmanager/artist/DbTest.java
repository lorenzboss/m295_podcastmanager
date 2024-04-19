package ch.bossgroup.podcastmanager.artist;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.annotation.Rollback;

import java.util.Optional;

@DataJpaTest()
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@Rollback(false)
public class DbTest {

    private final String firstName1 = "Lorenz";
    private final String lastName1 = "Boss";
    private final Artist artist1 = new Artist(firstName1, lastName1);

    private final String firstName2 = "Cem";
    private final String lastName2 = "Akkaya";
    private final Artist artist2 = new Artist(firstName2, lastName2);

    @Autowired
    private ArtistRepository repository;

    @Test
    void insertArtist() {
        Artist dbArtist1 = this.repository.save(artist1);
        Assertions.assertNotNull(dbArtist1.getId());

        Artist dbArtist2 = this.repository.save(artist2);
        Assertions.assertNotNull(dbArtist2.getId());
    }

    @Test
    void getArtist() {
        Artist dbArtist = this.repository.save(artist1);
        Artist foundArtist = this.repository.findById(dbArtist.getId()).orElse(null);
        Assertions.assertEquals(artist1, foundArtist);
    }

    @Test
    void updateArtist() {
        Artist dbArtist = this.repository.save(artist1);
        Artist artistToUpdate = this.repository.findById(dbArtist.getId()).orElse(null);
        artistToUpdate.setFirstname(firstName2);
        this.repository.save(artistToUpdate);
        Artist foundArtist = this.repository.findById(artistToUpdate.getId()).orElse(null);
        Assertions.assertEquals(artistToUpdate, foundArtist);
    }

    @Test
    void deleteArtist() {
        Artist dbArtist = this.repository.save(artist1);
        this.repository.delete(dbArtist);
        Optional<Artist> deletedArtist = this.repository.findById(dbArtist.getId());
        Assertions.assertFalse(deletedArtist.isPresent());
    }
}
