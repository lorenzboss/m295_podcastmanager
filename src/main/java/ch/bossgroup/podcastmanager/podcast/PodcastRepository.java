package ch.bossgroup.podcastmanager.podcast;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PodcastRepository extends JpaRepository<Podcast, Long> {

    List<Podcast> findByOrderById();
}