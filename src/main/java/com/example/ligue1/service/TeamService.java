package com.example.ligue1.service;

import com.example.ligue1.dto.in.TeamDtoIn;
import com.example.ligue1.dto.out.TeamDtoOut;
import com.example.ligue1.model.Player;
import com.example.ligue1.model.Team;
import com.example.ligue1.repository.PlayerRepository;
import com.example.ligue1.repository.TeamRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Slf4j
public class TeamService {

    private final TeamRepository teamRepository;
    private final PlayerRepository playerRepository;

    public TeamService(TeamRepository teamRepository, PlayerRepository playerRepository) {
        this.teamRepository = teamRepository;
        this.playerRepository = playerRepository;
    }

    public TeamDtoOut saveNewTeam(TeamDtoIn newTeam){

        log.info("save new team : {}",newTeam);
        Team team =Team.builder()
                .name(newTeam.getName())
                .acronym(newTeam.getAcronym())
                .budget(newTeam.getBudget())
                .build();
        Team savedTeam = teamRepository.save(team);

        for(Player player:newTeam.getPlayers()){
            Player newPlayer = Player.builder()
                    .name(player.getName())
                    .position(player.getPosition())
                    .team(savedTeam)
                    .build();
            playerRepository.save(newPlayer);
        }

        TeamDtoOut teamDtoOut = TeamDtoOut.builder()
                .id(savedTeam.getId())
                .name(savedTeam.getName())
                .acronym(savedTeam.getAcronym())
                .budget(savedTeam.getBudget())
                .build();
        return  teamDtoOut;
    }

    public List<TeamDtoOut> findAll(Pageable pageable) {

        Page<Team> teams = teamRepository.findAll(pageable);

        return teamRepository.findAll(pageable).getContent().stream().map(team -> TeamDtoOut.builder()
                .id(team.getId())
                .name(team.getName())
                .budget(team.getBudget())
                .acronym(team.getAcronym())
                .players(team.getPlayers())
                .build()

        ).collect(Collectors.toList());
    }

    public Team getById(Long id){
        Team t = teamRepository.findById(id).get();

        return teamRepository.findById(id).get();
    }
}
