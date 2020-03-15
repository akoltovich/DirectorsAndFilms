package by.akoltovich.controller;

import by.akoltovich.DAO.Director;
import by.akoltovich.repo.DirectorRepo;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class DirectorController {

    private final DirectorRepo directorRepo;

    public DirectorController(DirectorRepo directorRepo) {
        this.directorRepo = directorRepo;
    }

    @GetMapping("/directors")
    public List<Director> getAllDirectors() {
        return directorRepo.findAll();
    }

    @GetMapping("/directors/byId")
    public Director getDirectorById(@RequestBody Long id) {
        directorRepo.findById(id);
        if (!directorRepo.findById(id).isPresent()) {
            throw new NullPointerException();
        }
        return directorRepo.findById(id).get();
    }

    @PostMapping("/directors/add")
    public Director addDirector(@RequestBody Director director) {
        return directorRepo.save(director);
    }

    @PutMapping("/directors/change/byId")
    public Director updateDirector(@RequestBody Director newDirector, @PathVariable Long id) {
        return directorRepo.findById(id).map(director -> {
            newDirector.setFirstName(newDirector.getFirstName());
            newDirector.setLastName(newDirector.getLastName());
            newDirector.setBirthDate(newDirector.getBirthDate());
            return directorRepo.save(director);
        }).orElseGet(() -> {
            newDirector.setId(newDirector.getId());
            return directorRepo.save(newDirector);
        });
    }

    @DeleteMapping("/directors/delete")
    public void deleteDirector(@RequestBody Long id) {
        directorRepo.deleteById(id);
    }
}
