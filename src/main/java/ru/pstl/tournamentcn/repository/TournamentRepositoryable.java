package ru.pstl.tournamentcn.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.pstl.tournamentcn.model.Tournament;

public interface TournamentRepositoryable extends JpaRepository<Tournament, Long> {
}
