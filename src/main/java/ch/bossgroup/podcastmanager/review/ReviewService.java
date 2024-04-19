package ch.bossgroup.podcastmanager.review;

import ch.bossgroup.podcastmanager.podcast.Podcast;
import ch.bossgroup.podcastmanager.podcast.PodcastRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;

@Service
public class ReviewService {

    private final ReviewRepository repository;
    private final PodcastRepository podcastRepository;

    public ReviewService(ReviewRepository repository, PodcastRepository podcastRepository) {
        this.repository = repository;
        this.podcastRepository = podcastRepository;
    }

    public List<Review> getAllReviews() {
        return repository.findByOrderById();
    }

    public Review getReview(Long id) {
        return repository.findById(id).orElse(null);
    }

    public List<Review> getReviewForPodcastId(Long id) {
        if (podcastRepository.existsById(id)) {
            Podcast podcast = podcastRepository.findById(id).orElse(null);
            return repository.findByPodcastOrderById(podcast);
        } else throw new NoSuchElementException("Object with Id: " + id + " does not exist!");
    }

    public Review saveReview(Review review) {
        return repository.save(review);
    }

    public Review editReview(Review review, Long id) {
        if (repository.existsById(id)) {
            Review newReview = new Review(id, review.getTitle(), review.getText(), review.getWriter(), review.getPodcast());
            return repository.save(newReview);
        } else throw new NoSuchElementException("Object with Id: " + id + " does not exist!");
    }

    public void deleteReview(Long id) {
        if (repository.existsById(id)) {
            repository.deleteById(id);
        } else throw new NoSuchElementException("Object with Id: " + id + " does not exist!");
    }
}
