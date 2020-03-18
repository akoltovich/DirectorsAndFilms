package by.akoltovich.service;

import by.akoltovich.domain.Director;
import by.akoltovich.repository.DirectorRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class DirectorService {
    private final DirectorRepository directorRepository;

    public DirectorService(DirectorRepository directorRepository) {
        this.directorRepository = directorRepository;
    }

    @Transactional
    public List<Director> getAllDirectors() {
        return directorRepository.findAll();
    }

    @Transactional
    public Director getDirectorById(Long id) {
        directorRepository.findById(id);
        if (!directorRepository.findById(id).isPresent()) {
            throw new NullPointerException();
        }
        return directorRepository.findById(id).get();
    }

    @Transactional
    public Director addDirector(Director director) {
        return directorRepository.save(director);
    }

    @Transactional
    public Director updateDirector(Director newDirector, Long id) {
        return directorRepository.findById(id).map(director -> {
            newDirector.setFirstName(newDirector.getFirstName());
            newDirector.setLastName(newDirector.getLastName());
            newDirector.setBirthDate(newDirector.getBirthDate());
            return directorRepository.save(director);
        }).orElseGet(() -> {
            newDirector.setId(newDirector.getId());
            return directorRepository.save(newDirector);
        });
    }

    @Transactional
    public void deleteDirector(Long id) {
        directorRepository.deleteById(id);
    }
}
