package ch.bossgroup.podcastmanager.topic;

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
public class TopicController {

    private final TopicService topicService;


    public TopicController(TopicService topicService) {
        this.topicService = topicService;
    }

    @GetMapping("/api/topic/{id}")
    @RolesAllowed(Roles.user)
    public ResponseEntity<Topic> getTopic(@PathVariable Long id) {
        return new ResponseEntity<>(topicService.getTopic(id), HttpStatus.OK);
    }

    @GetMapping("/api/topic")
    @RolesAllowed(Roles.user)
    public ResponseEntity<List<Topic>> getAllTopic() {
        return new ResponseEntity<>(topicService.getAllTopics(), HttpStatus.OK);
    }

    @PostMapping("/api/topic")
    @RolesAllowed(Roles.user)
    public ResponseEntity<Topic> createTopic(@RequestBody Topic topic) {
        return new ResponseEntity<>(topicService.saveTopic(topic), HttpStatus.CREATED);
    }

    @PutMapping("/api/topic/{id}")
    @RolesAllowed(Roles.user)
    public ResponseEntity<Topic> editTopic(@PathVariable Long id, @RequestBody Topic topic) {
        return new ResponseEntity<>(topicService.editTopic(topic, id), HttpStatus.OK);
    }

    @DeleteMapping("/api/topic/{id}")
    @RolesAllowed(Roles.user)
    public ResponseEntity<Topic> deleteTopic(@PathVariable Long id) {
        topicService.deleteTopic(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }


}
