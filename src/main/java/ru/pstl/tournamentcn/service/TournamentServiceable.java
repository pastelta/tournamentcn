package ru.pstl.tournamentcn.service;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import ru.pstl.tournamentcn.model.Tournament;

public interface TournamentServiceable {
    ResponseEntity<Tournament> getTournamentById(long tournamentId);
    ResponseEntity<Tournament> createTournament(Tournament tournament);
}
