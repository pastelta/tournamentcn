package ru.pstl.tournamentcn.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.pstl.tournamentcn.model.Participant;

public interface ParticipantRepositoryable extends JpaRepository<Participant, Long> {
    boolean existsByTournamentIdAndPlayerId(Long tournamentId, Long playerId);
    Participant findByTournamentIdAndPlayerId(Long tournamentId, Long playerId);
}
