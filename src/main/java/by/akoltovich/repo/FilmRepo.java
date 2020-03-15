package by.akoltovich.repo;

import by.akoltovich.DAO.Film;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FilmRepo extends JpaRepository<Film, Long> {
}
