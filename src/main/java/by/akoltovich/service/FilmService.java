package by.akoltovich.service;

import by.akoltovich.domain.Film;
import by.akoltovich.repository.FilmRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class FilmService {

    private final FilmRepository filmRepository;

    public FilmService(FilmRepository filmRepository) {
        this.filmRepository = filmRepository;
    }

    @Transactional
    public List<Film> getAllFilm() {
        return filmRepository.findAll();
    }

    @Transactional
    public Film getFilmById(Long id) {
        filmRepository.findById(id);
        if (!filmRepository.findById(id).isPresent()) {
            throw new NullPointerException();
        }
        return filmRepository.findById(id).get();
    }

    @Transactional
    public Film addFilm(Film film) {
        return filmRepository.save(film);
    }

    @Transactional
    public Film updateFilm(Film newFilm, Long id) {
        return filmRepository.findById(id).map(film -> {
            newFilm.setDirector(newFilm.getDirector());
            newFilm.setName(newFilm.getName());
            newFilm.setGenre(newFilm.getGenre());
            newFilm.setReleaseDate(newFilm.getReleaseDate());
            return filmRepository.save(film);
        }).orElseGet(() -> {
            newFilm.setId(newFilm.getId());
            return filmRepository.save(newFilm);
        });
    }

    @Transactional
    public void deleteFilm(Long id) {
        filmRepository.deleteById(id);
    }
}
