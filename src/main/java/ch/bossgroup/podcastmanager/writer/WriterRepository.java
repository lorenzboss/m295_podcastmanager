package ch.bossgroup.podcastmanager.writer;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface WriterRepository extends JpaRepository<Writer, Long> {

    List<Writer> findByOrderById();
}
