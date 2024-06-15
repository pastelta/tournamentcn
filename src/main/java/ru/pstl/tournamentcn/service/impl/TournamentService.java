package ru.pstl.tournamentcn.service.impl;

import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import ru.pstl.tournamentcn.exception.ResourceNotFoundException;
import ru.pstl.tournamentcn.model.Tournament;
import ru.pstl.tournamentcn.repository.TournamentRepositoryable;
import ru.pstl.tournamentcn.service.TournamentServiceable;

@Service
@AllArgsConstructor
public class TournamentService implements TournamentServiceable {
    private final TournamentRepositoryable TOURNAMENT_REPO;
    @Override
    public ResponseEntity<Tournament> getTournamentById(long tournamentId) {
        Tournament tournament = TOURNAMENT_REPO.findById(tournamentId)
                .orElseThrow(()-> new ResourceNotFoundException("Not found Tournament with id = " + tournamentId));
        return new ResponseEntity<>(tournament, HttpStatus.OK);
    }
    @Override
    public ResponseEntity<Tournament> createTournament(Tournament tournament) {
        return new ResponseEntity<>(TOURNAMENT_REPO.save(tournament), HttpStatus.OK);
    }
}
