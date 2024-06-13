package ru.pstl.tournamentcn.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.pstl.tournamentcn.model.Participant;

import java.util.List;

public interface ParticipantRepositoryable extends JpaRepository<Participant, Long> {
    Participant findByTournamentIdAndPlayerId(Long tournamentId, Long playerId);
    Boolean existsByTournamentIdAndPlayerId(Long tournamentId, Long playerId);
    List<Participant> findByTournamentId(Long tournamentId);
}
