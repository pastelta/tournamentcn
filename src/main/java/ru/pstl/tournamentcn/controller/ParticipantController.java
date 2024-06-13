package ru.pstl.tournamentcn.controller;

import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.pstl.tournamentcn.exception.ResourceNotFoundException;
import ru.pstl.tournamentcn.model.Participant;
import ru.pstl.tournamentcn.repository.ParticipantRepositoryable;
import ru.pstl.tournamentcn.repository.TournamentRepositoryable;

import java.util.ArrayList;
import java.util.List;

@CrossOrigin(origins = "http://localhost:8080/")
@RestController
@RequestMapping("/api/v1")
@AllArgsConstructor
public class ParticipantController {
    @Autowired
    private TournamentRepositoryable tournamentRepo;
    @Autowired
    private ParticipantRepositoryable participantRepo;
    @GetMapping("/tournaments/{tournamentId}/participants")
    public ResponseEntity<List<Participant>> getAllParticipantsByTournamentId(@PathVariable(value = "tournamentId") Long tournamentId){
        if(!tournamentRepo.existsById(tournamentId)){
            throw new ResourceNotFoundException("Not found Tournament with id = " + tournamentId);
        }
        List<Participant> participants = participantRepo.findByTournamentId(tournamentId);
        return new ResponseEntity<>(participants, HttpStatus.OK);
    }
    @PostMapping("/tournaments/{tournamentId}/participant")
    public ResponseEntity<Participant> createOrUpdateParticipant(@PathVariable(value = "tournamentId") Long tournamentId,
                                                         @RequestBody Participant participantRequest){
        if(participantRepo.existsByTournamentIdAndPlayerId(tournamentId, participantRequest.getPlayerId())) {
            Participant pEx = participantRepo.findByTournamentIdAndPlayerId(tournamentId, participantRequest.getPlayerId());
            pEx.setCntIn(participantRequest.getCntIn());
            participantRepo.save(pEx);
            return new ResponseEntity<>(pEx, HttpStatus.OK);
        }
        Participant participant = tournamentRepo.findById(tournamentId).map(tournament -> {
            participantRequest.setTournament(tournament);
            return participantRepo.save(participantRequest);
        }).orElseThrow(()->new ResourceNotFoundException("Not found Tournament with id = " + tournamentId));
        return new ResponseEntity<>(participant, HttpStatus.CREATED);
    }

    @PostMapping("/tournaments/{tournamentId}/participants")
    public ResponseEntity<List<Participant>> createOrUpdateParticipants(@PathVariable(value = "tournamentId") Long tournamentId,
                                                                        @RequestBody List<Participant> participantListRequest){
        List<Participant> participants = new ArrayList<>();
        for(Participant p : participantListRequest){
            if(participantRepo.existsByTournamentIdAndPlayerId(tournamentId, p.getPlayerId())) {
                Participant pEx = participantRepo.findByTournamentIdAndPlayerId(tournamentId, p.getPlayerId());
                pEx.setCntIn(p.getCntIn());
                participantRepo.save(pEx);
                participants.add(pEx);
            } else {
                p.setTournament(tournamentRepo.findById(tournamentId).orElseThrow(()->new ResourceNotFoundException("Not found Tournament with id = " + tournamentId)));
                participantRepo.save(p);
                participants.add(p);
            }
        }
        return new ResponseEntity<>(participants, HttpStatus.OK);
    }
}
