package by.akoltovich.controller;

import by.akoltovich.domain.Film;
import by.akoltovich.service.FilmService;
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

    private final FilmService filmService;

    public FilmController(FilmService filmService) {
        this.filmService = filmService;
    }

    @GetMapping("/films")
    public List<Film> getAllFilm() {
        return filmService.getAllFilm();
    }

    @GetMapping("/films/byId")
    public Film getFilmById(@RequestBody Long id) {
        return filmService.getFilmById(id);
    }

    @PostMapping("/films/add")
    public Film addFilm(@RequestBody Film film) {
        return filmService.addFilm(film);
    }

    @PutMapping("/films/update")
    public Film updateFilm(@RequestBody Film newFilm, @PathVariable Long id) {
        return filmService.updateFilm(newFilm, id);
    }

    @DeleteMapping("/films/delete")
    public void deleteFilm(@RequestBody Long id) {
        filmService.deleteFilm(id);
    }
}
