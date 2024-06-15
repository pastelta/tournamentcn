package ru.pstl.tournamentcn.controller;

import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.pstl.tournamentcn.model.Participant;
import ru.pstl.tournamentcn.model.Player;
import ru.pstl.tournamentcn.model.Tournament;
import ru.pstl.tournamentcn.service.ParticipantServiceable;
import ru.pstl.tournamentcn.service.PlayerServiceable;
import ru.pstl.tournamentcn.service.TournamentServiceable;

import java.util.List;

@CrossOrigin(origins = "http://localhost:8080/")
@RestController
@RequestMapping("/api/v1")
@AllArgsConstructor
public class TournamentcnController {
    private final TournamentServiceable TOURNAMENT_SERVICE;
    private final PlayerServiceable PLAYER_SERVICE;
    private final ParticipantServiceable PARTICIPANT_SERVICE;

    @GetMapping("/tournaments/{tournamentId}")
    public ResponseEntity<Tournament> getTournamentById(@PathVariable("tournamentId") long tournamentId){
        return TOURNAMENT_SERVICE.getTournamentById(tournamentId);
    }
    @PostMapping("/tournament")
    public ResponseEntity<Tournament> createTournament(@RequestBody Tournament tournament){
        return TOURNAMENT_SERVICE.createTournament(tournament);
    }

    @GetMapping("/player-list")
    public ResponseEntity<List<Player>> getPlayerList(){
        return PLAYER_SERVICE.getPlayerList();
    }

    @PostMapping("/tournaments/{tournamentId}/participant")
    public ResponseEntity<Participant> createOrUpdateParticipant (@PathVariable("tournamentId")long tournamentId, @RequestBody Player player){
        return PARTICIPANT_SERVICE.createOrUpdateParticipant(tournamentId, player);
    }
    @PostMapping("/tournaments/{tournamentId}/participant-list")
    public ResponseEntity<List<Participant>> createOrUpdateParticipantList (@PathVariable("tournamentId")long tournamentId, @RequestBody List<Player> playerList){
        return PARTICIPANT_SERVICE.createOrUpdateParticipantList(tournamentId, playerList);
    }
}
