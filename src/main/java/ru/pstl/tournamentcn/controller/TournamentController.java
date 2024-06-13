package ru.pstl.tournamentcn.controller;

import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.pstl.tournamentcn.TournamentcnApplication;
import ru.pstl.tournamentcn.exception.ResourceNotFoundException;
import ru.pstl.tournamentcn.model.Tournament;
import ru.pstl.tournamentcn.repository.TournamentRepositoryable;

import java.util.ArrayList;
import java.util.List;

@CrossOrigin(origins = "http://localhost:8080/")
@RestController
@RequestMapping("/api/v1")
@AllArgsConstructor
public class TournamentController {
    @Autowired
    TournamentRepositoryable tournamentRepo;
    @GetMapping("/tournaments/{id}")
    public ResponseEntity<Tournament> getTournamentById(@PathVariable("id") long id){
        Tournament tournament = tournamentRepo.findById(id)
                .orElseThrow(()-> new ResourceNotFoundException("Not found Tournament with id = " + id));
        return new ResponseEntity<>(tournament, HttpStatus.OK);
    }
    @GetMapping("/tournaments/status/{statusId}")
    public ResponseEntity<List<Tournament>> getTournamentsByStatusId(@PathVariable("statusId") long statusId){
        List<Tournament> tournaments = tournamentRepo.findByStatusId(statusId);

        return new ResponseEntity<>(tournaments, HttpStatus.OK);
    }

}
