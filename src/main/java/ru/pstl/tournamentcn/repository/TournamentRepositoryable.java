package ru.pstl.tournamentcn.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.pstl.tournamentcn.model.Tournament;

import java.util.List;

public interface TournamentRepositoryable extends JpaRepository<Tournament, Long> {
    List<Tournament> findByStatusId (Long statusId);
}
