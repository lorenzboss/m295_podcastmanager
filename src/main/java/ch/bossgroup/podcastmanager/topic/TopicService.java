package ch.bossgroup.podcastmanager.topic;

import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;

@Service
public class TopicService {

    private final TopicRepository repository;

    public TopicService(TopicRepository repository) {
        this.repository = repository;
    }

    public List<Topic> getAllTopics() {
        return repository.findByOrderById();
    }

    public Topic getTopic(Long id) {
        return repository.findById(id).orElse(null);
    }

    public Topic saveTopic(Topic topic) {
        return repository.save(topic);
    }

    public Topic editTopic(Topic topic, Long id) {
        if (repository.existsById(id)) {
            Topic newTopic = new Topic(id, topic.getDescription());
            return repository.save(newTopic);
        } else throw new NoSuchElementException("Object with Id: " + id + " does not exist!");
    }

    public void deleteTopic(Long id) {
        if (repository.existsById(id)) {
            repository.deleteById(id);
        } else throw new NoSuchElementException("Object with Id: " + id + " does not exist!");
    }
}
