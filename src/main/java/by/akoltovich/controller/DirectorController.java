package by.akoltovich.controller;

import by.akoltovich.domain.Director;
import by.akoltovich.service.DirectorService;
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

    private final DirectorService directorService;

    public DirectorController(DirectorService directorService) {
        this.directorService = directorService;
    }

    @GetMapping("/directors")
    public List<Director> getAllDirectors() {
        return directorService.getAllDirectors();
    }

    @GetMapping("/directors/byId")
    public Director getDirectorById(@RequestBody Long id) {
        return directorService.getDirectorById(id);
    }

    @PostMapping("/directors/add")
    public Director addDirector(@RequestBody Director director) {
        return directorService.addDirector(director);
    }

    @PutMapping("/directors/update")
    public Director updateDirector(@RequestBody Director newDirector, @PathVariable Long id) {
        return directorService.updateDirector(newDirector, id);
    }

    @DeleteMapping("/directors/delete")
    public void deleteDirector(@RequestBody Long id) {
        directorService.deleteDirector(id);
    }
}
