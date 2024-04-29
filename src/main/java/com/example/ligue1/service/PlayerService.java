package com.example.ligue1.service;

import com.example.ligue1.dto.in.PlayerDtoIN;
import com.example.ligue1.dto.out.PlayerDtoOut;
import com.example.ligue1.model.Player;
import com.example.ligue1.model.Team;
import com.example.ligue1.repository.PlayerRepository;
import com.example.ligue1.repository.TeamRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class PlayerService {

    private final PlayerRepository playerRepository;
    private final TeamRepository teamRepository;

    public PlayerService(PlayerRepository playerRepository, TeamRepository teamRepository) {
        this.playerRepository = playerRepository;
        this.teamRepository = teamRepository;
    }

    public List<PlayerDtoOut> getAll(){
       List<Player> players = playerRepository.findAll();
       List<PlayerDtoOut> playerDtoOuts = players.stream().map(player -> PlayerDtoOut.builder()
               .id(player.getId())
                       .name(player.getName())
                               .position(player.getPosition())
                                       .team(player.getTeam()).
               build()).collect(Collectors.toList());
        return playerDtoOuts;
    }

    public PlayerDtoOut addPlayer(PlayerDtoIN newPlayer){
        Team playerTeam = teamRepository.findById((Long) newPlayer.getTeam_id()).get();
        Player player = Player.builder()
                .name(newPlayer.getName())
                .position(newPlayer.getPosition())
                .team(playerTeam)
                .build();

        player = playerRepository.save(player);
        PlayerDtoOut playerDtoOut = PlayerDtoOut.builder()
                .id(player.getId())
                .name(player.getName())
                .position(player.getPosition())
                .team(player.getTeam())
                .build();

        return playerDtoOut;
    }


    public PlayerDtoOut getById(String id) {
        Player player = playerRepository.findById(Long.valueOf(id)).orElseThrow(()-> new EntityNotFoundException("not found"));
        PlayerDtoOut playerDtoOut = PlayerDtoOut.builder()
                .id(player.getId())
                .name(player.getName())
                .position(player.getPosition())
                .build();

        return  playerDtoOut;
    }
}
