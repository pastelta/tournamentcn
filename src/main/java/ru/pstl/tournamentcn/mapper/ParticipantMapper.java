package ru.pstl.tournamentcn.mapper;

import ru.pstl.tournamentcn.model.InfoParticipantRequest;
import ru.pstl.tournamentcn.model.Participant;

public class ParticipantMapper {
    public static Participant mapInfoParticipantRequestToParticipant(InfoParticipantRequest infoParticipantRequest, Participant participant){
        participant.setCntIn(infoParticipantRequest.getCntIn());
        participant.setPlace(infoParticipantRequest.getPlace());
        participant.setPayout(infoParticipantRequest.getPayout());
        return participant;
    }
}
