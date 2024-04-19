package ch.bossgroup.podcastmanager.review;

import ch.bossgroup.podcastmanager.podcast.Podcast;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ReviewRepository extends JpaRepository<Review, Long> {

    List<Review> findByOrderById();

    List<Review> findByPodcastOrderById(Podcast podcast);
}
