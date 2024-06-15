package ru.pstl.tournamentcn.service.impl;

import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import ru.pstl.tournamentcn.exception.ResourceNotFoundException;
import ru.pstl.tournamentcn.model.Participant;
import ru.pstl.tournamentcn.model.Player;
import ru.pstl.tournamentcn.model.Tournament;
import ru.pstl.tournamentcn.repository.ParticipantRepositoryable;
import ru.pstl.tournamentcn.repository.TournamentRepositoryable;
import ru.pstl.tournamentcn.service.ParticipantServiceable;

import java.util.ArrayList;
import java.util.List;

@Service
@AllArgsConstructor
public class ParticipantService implements ParticipantServiceable {
    private final TournamentRepositoryable TOURNAMENT_REPO;
    private final ParticipantRepositoryable PARTICIPANT_REPO;
    @Override
    public ResponseEntity<Participant> createOrUpdateParticipant(long tournamentId, Player player) {
        Tournament tournament = TOURNAMENT_REPO.findById(tournamentId)
                .orElseThrow(()-> new ResourceNotFoundException("Not found Tournament with id = " + tournamentId));
        Participant participant = new Participant();

        if(!PARTICIPANT_REPO.existsByTournamentIdAndPlayerId(tournamentId, player.getId())){
            participant.setTournament(tournament);
            participant.setPlayer(player);
            PARTICIPANT_REPO.save(participant);
        }
        return new ResponseEntity<>(participant, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<List<Participant>> createOrUpdateParticipantList(long tournamentId, List<Player> playerList) {
        Tournament tournament = TOURNAMENT_REPO.findById(tournamentId)
                .orElseThrow(()-> new ResourceNotFoundException("Not found Tournament with id = " + tournamentId));
        List<Participant> participantList = new ArrayList<>();
        for(Player p : playerList){
            if(!PARTICIPANT_REPO.existsByTournamentIdAndPlayerId(tournamentId, p.getId())) {
                Participant participant = new Participant();
                participant.setTournament(tournament);
                participant.setPlayer(p);
                PARTICIPANT_REPO.save(participant);
                participantList.add(participant);
            }
        }
        return new ResponseEntity<>(participantList, HttpStatus.OK);
    }
}
