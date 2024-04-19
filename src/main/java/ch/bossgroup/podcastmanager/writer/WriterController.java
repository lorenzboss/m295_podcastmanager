package ch.bossgroup.podcastmanager.writer;

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
public class WriterController {

    private final WriterService writerService;


    public WriterController(WriterService writerService) {
        this.writerService = writerService;
    }

    @GetMapping("/api/writer/{id}")
    @RolesAllowed(Roles.user)
    public ResponseEntity<Writer> getWriter(@PathVariable Long id) {
        return new ResponseEntity<>(writerService.getWriter(id), HttpStatus.OK);
    }

    @GetMapping("/api/writer")
    @RolesAllowed(Roles.user)
    public ResponseEntity<List<Writer>> getAllWriter() {
        return new ResponseEntity<>(writerService.getAllWriters(), HttpStatus.OK);
    }

    @PostMapping("/api/writer")
    @RolesAllowed(Roles.user)
    public ResponseEntity<Writer> createWriter(@RequestBody Writer writer) {
        return new ResponseEntity<>(writerService.saveWriter(writer), HttpStatus.CREATED);
    }

    @PutMapping("/api/writer/{id}")
    @RolesAllowed(Roles.staff)
    public ResponseEntity<Writer> editWriter(@PathVariable Long id, @RequestBody Writer writer) {
        return new ResponseEntity<>(writerService.editWriter(writer, id), HttpStatus.OK);
    }

    @DeleteMapping("/api/writer/{id}")
    @RolesAllowed(Roles.staff)
    public ResponseEntity<Writer> deleteWriter(@PathVariable Long id) {
        writerService.deleteWriter(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }


}
