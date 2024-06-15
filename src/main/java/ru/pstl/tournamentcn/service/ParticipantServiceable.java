package ru.pstl.tournamentcn.service;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import ru.pstl.tournamentcn.model.InfoParticipantRequest;
import ru.pstl.tournamentcn.model.Participant;
import ru.pstl.tournamentcn.model.Player;

import java.util.List;

public interface ParticipantServiceable {
    ResponseEntity<Participant> createOrUpdateParticipant (long tournamentId, InfoParticipantRequest infoParticipantRequest);
    ResponseEntity<List<Participant>> createOrUpdateParticipantList (long tournamentId, List<InfoParticipantRequest> infoParticipantRequestList);
}
