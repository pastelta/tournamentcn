package ru.pstl.tournamentcn.service.impl;

import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import ru.pstl.tournamentcn.model.Player;
import ru.pstl.tournamentcn.repository.PlayerRepositoryable;
import ru.pstl.tournamentcn.service.PlayerServiceable;

import java.util.List;

@Service
@AllArgsConstructor
public class PlayerService implements PlayerServiceable {
    private final PlayerRepositoryable PLAYER_REPO;
    @Override
    public ResponseEntity<List<Player>> getPlayerList() {
        List<Player> playerList = PLAYER_REPO.findAll();
        return new ResponseEntity<>(playerList, HttpStatus.OK);
    }
}
