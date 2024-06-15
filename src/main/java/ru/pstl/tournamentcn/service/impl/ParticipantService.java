package ru.pstl.tournamentcn.service.impl;

import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import ru.pstl.tournamentcn.exception.ResourceNotFoundException;
import ru.pstl.tournamentcn.mapper.ParticipantMapper;
import ru.pstl.tournamentcn.model.InfoParticipantRequest;
import ru.pstl.tournamentcn.model.Participant;
import ru.pstl.tournamentcn.model.Player;
import ru.pstl.tournamentcn.model.Tournament;
import ru.pstl.tournamentcn.repository.ParticipantRepositoryable;
import ru.pstl.tournamentcn.repository.PlayerRepositoryable;
import ru.pstl.tournamentcn.repository.TournamentRepositoryable;
import ru.pstl.tournamentcn.service.ParticipantServiceable;

import java.util.ArrayList;
import java.util.List;

@Service
@AllArgsConstructor
public class ParticipantService implements ParticipantServiceable {
    private final TournamentRepositoryable TOURNAMENT_REPO;
    private final ParticipantRepositoryable PARTICIPANT_REPO;
    private final PlayerRepositoryable PLAYER_REPO;
    @Override
    public ResponseEntity<Participant> createOrUpdateParticipant(long tournamentId, InfoParticipantRequest infoParticipantRequest) {
        Participant participant = new Participant();
        Tournament tournament = TOURNAMENT_REPO.findById(tournamentId)
                .orElseThrow(()-> new ResourceNotFoundException("Not found Tournament with id = " + tournamentId));
        Player player = PLAYER_REPO.findById(infoParticipantRequest.getPlayerId())
                .orElseThrow(()-> new ResourceNotFoundException("Not found Player with id = " + infoParticipantRequest.getPlayerId()));

        if(PARTICIPANT_REPO.existsByTournamentIdAndPlayerId(tournamentId, infoParticipantRequest.getPlayerId())) {
            participant = PARTICIPANT_REPO.findByTournamentIdAndPlayerId(tournamentId, infoParticipantRequest.getPlayerId());
        } else {
            participant.setTournament(tournament);
            participant.setPlayer(player);
        }
        participant = ParticipantMapper.mapInfoParticipantRequestToParticipant(infoParticipantRequest, participant);
        PARTICIPANT_REPO.save(participant);
        return new ResponseEntity<>(participant, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<List<Participant>> createOrUpdateParticipantList(long tournamentId, List<InfoParticipantRequest> infoParticipantRequestList) {
        Tournament tournament = TOURNAMENT_REPO.findById(tournamentId)
                .orElseThrow(()-> new ResourceNotFoundException("Not found Tournament with id = " + tournamentId));
        List<Participant> participantList = new ArrayList<>();
        for(InfoParticipantRequest infoParticipantRequest : infoParticipantRequestList){
            Participant participant = new Participant();
            Player player = PLAYER_REPO.findById(infoParticipantRequest.getPlayerId())
                    .orElseThrow(()-> new ResourceNotFoundException("Not found Player with id = " + infoParticipantRequest.getPlayerId()));

            if(PARTICIPANT_REPO.existsByTournamentIdAndPlayerId(tournamentId, infoParticipantRequest.getPlayerId())) {
                participant = PARTICIPANT_REPO.findByTournamentIdAndPlayerId(tournamentId, infoParticipantRequest.getPlayerId());
            } else {
                participant.setTournament(tournament);
                participant.setPlayer(player);
            }
            participant = ParticipantMapper.mapInfoParticipantRequestToParticipant(infoParticipantRequest, participant);
            PARTICIPANT_REPO.save(participant);
            participantList.add(participant);
        }
        return new ResponseEntity<>(participantList, HttpStatus.OK);
    }
}
