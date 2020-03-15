package by.akoltovich.repo;

import by.akoltovich.DAO.Director;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DirectorRepo extends JpaRepository<Director, Long> {
}
