package ru.pstl.tournamentcn.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.pstl.tournamentcn.model.Player;

public interface PlayerRepositoryable extends JpaRepository<Player, Long> {
}
