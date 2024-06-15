package ru.pstl.tournamentcn.service;

import org.springframework.http.ResponseEntity;
import ru.pstl.tournamentcn.model.Player;

import java.util.List;

public interface PlayerServiceable {
    ResponseEntity<List<Player>> getPlayerList();
}
