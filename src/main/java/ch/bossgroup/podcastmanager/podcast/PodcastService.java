package ch.bossgroup.podcastmanager.podcast;

import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;

@Service
public class PodcastService {

    private final PodcastRepository repository;

    public PodcastService(PodcastRepository repository) {
        this.repository = repository;
    }

    public List<Podcast> getAllPodcasts() {
        return repository.findByOrderById();
    }

    public Podcast getPodcast(Long id) {
        return repository.findById(id).orElse(null);
    }

    public Podcast savePodcast(Podcast podcast) {
        return repository.save(podcast);
    }

    public Podcast editPodcast(Podcast podcast, Long id) {
        if (repository.existsById(id)) {
            Podcast newPodcast = new Podcast(id, podcast.getTitle(), podcast.getDescription(), podcast.getArtists(), podcast.getTopics());
            return repository.save(newPodcast);
        } else throw new NoSuchElementException("Object with Id: " + id + " does not exist!");
    }

    public void deletePodcast(Long id) {
        if (repository.existsById(id)) {
            repository.deleteById(id);
        } else throw new NoSuchElementException("Object with Id: " + id + " does not exist!");
    }
}
