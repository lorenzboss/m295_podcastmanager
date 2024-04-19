package ch.bossgroup.podcastmanager.review;

import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;

@Service
public class ReviewService {

    private final ReviewRepository repository;

    public ReviewService(ReviewRepository repository) {
        this.repository = repository;
    }

    public List<Review> getAllReviews() {
        return repository.findByOrderById();
    }

    public Review getReview(Long id) {
        return repository.findById(id).orElse(null);
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
