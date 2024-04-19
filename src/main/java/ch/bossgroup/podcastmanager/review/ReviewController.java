package ch.bossgroup.podcastmanager.review;

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
public class ReviewController {

    private final ReviewService reviewService;


    public ReviewController(ReviewService reviewService) {
        this.reviewService = reviewService;
    }

    @GetMapping("/api/review/{id}")
    @RolesAllowed(Roles.user)
    public ResponseEntity<Review> getReview(@PathVariable Long id) {
        return new ResponseEntity<>(reviewService.getReview(id), HttpStatus.OK);
    }

    @GetMapping("/api/review")
    @RolesAllowed(Roles.user)
    public ResponseEntity<List<Review>> getAllReview() {
        return new ResponseEntity<>(reviewService.getAllReviews(), HttpStatus.OK);
    }

    @GetMapping("/api/podcastreview/{id}")
    @RolesAllowed(Roles.user)
    public ResponseEntity<List<Review>> getReviewForPodcastId(@PathVariable Long id) {
        return new ResponseEntity<>(reviewService.getReviewForPodcastId(id), HttpStatus.OK);
    }

    @PostMapping("/api/review")
    @RolesAllowed(Roles.user)
    public ResponseEntity<Review> createReview(@RequestBody Review review) {
        return new ResponseEntity<>(reviewService.saveReview(review), HttpStatus.CREATED);
    }

    @PutMapping("/api/review/{id}")
    @RolesAllowed(Roles.staff)
    public ResponseEntity<Review> editReview(@PathVariable Long id, @RequestBody Review review) {
        return new ResponseEntity<>(reviewService.editReview(review, id), HttpStatus.OK);
    }

    @DeleteMapping("/api/review/{id}")
    @RolesAllowed(Roles.staff)
    public ResponseEntity<Review> deleteReview(@PathVariable Long id) {
        reviewService.deleteReview(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }


}
