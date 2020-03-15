package by.akoltovich.controller;

import by.akoltovich.DAO.Film;
import by.akoltovich.repo.FilmRepo;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class FilmController {
    private final FilmRepo filmRepo;

    public FilmController(FilmRepo filmRepo) {
        this.filmRepo = filmRepo;
    }

    @GetMapping("/films")
    public List<Film> getAllFilm() {
        return filmRepo.findAll();
    }

    @GetMapping("/films/byId")
    public Film getFilmById(@RequestBody Long id) {
        filmRepo.findById(id);
        if (!filmRepo.findById(id).isPresent()) {
            throw new NullPointerException();
        }
        return filmRepo.findById(id).get();
    }

    @PostMapping("/films/add")
    public Film addFilm(@RequestBody Film film) {
        return filmRepo.save(film);
    }

    @PutMapping("/update/film")
    public Film updateFilm(@RequestBody Film newFilm, @PathVariable Long id) {
        return filmRepo.findById(id).map(film -> {
            newFilm.setDirector(newFilm.getDirector());
            newFilm.setName(newFilm.getName());
            newFilm.setGenre(newFilm.getGenre());
            newFilm.setReleaseDate(newFilm.getReleaseDate());
            return filmRepo.save(film);
        }).orElseGet(() -> {
            newFilm.setId(newFilm.getId());
            return filmRepo.save(newFilm);
        });
    }

    @DeleteMapping("/films/delete")
    public void deleteFilm(@RequestBody Long id) {
        filmRepo.deleteById(id);
    }
}
