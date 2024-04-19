package ch.bossgroup.podcastmanager.writer;

import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;

@Service
public class WriterService {

    private final WriterRepository repository;

    public WriterService(WriterRepository repository) {
        this.repository = repository;
    }

    public List<Writer> getAllWriters() {
        return repository.findByOrderById();
    }

    public Writer getWriter(Long id) {
        return repository.findById(id).orElse(null);
    }

    public Writer saveWriter(Writer writer) {
        return repository.save(writer);
    }

    public Writer editWriter(Writer writer, Long id) {
        if (repository.existsById(id)) {
            Writer newWriter = new Writer(id, writer.getFirstname(), writer.getLastname());
            return repository.save(newWriter);
        } else throw new NoSuchElementException("Object with Id: " + id + " does not exist!");
    }

    public void deleteWriter(Long id) {
        if (repository.existsById(id)) {
            repository.deleteById(id);
        } else throw new NoSuchElementException("Object with Id: " + id + " does not exist!");
    }
}
