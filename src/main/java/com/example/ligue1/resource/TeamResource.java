package com.example.ligue1.resource;

import com.example.ligue1.dto.in.TeamDtoIn;
import com.example.ligue1.dto.out.TeamDtoOut;
import com.example.ligue1.model.Team;
import com.example.ligue1.service.TeamService;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/team")
public class TeamResource {

    private final TeamService teamService;

    public TeamResource(TeamService teamService) {
        this.teamService = teamService;
    }

    @PostMapping("")
    public ResponseEntity<TeamDtoOut> saveTeam(@RequestBody TeamDtoIn newTeam){

        TeamDtoOut savedTeam = teamService.saveNewTeam(newTeam);
        return ResponseEntity.ok(savedTeam);
    }

    @GetMapping("")
    public ResponseEntity<List<TeamDtoOut>> getAllTeams(
            @RequestParam(defaultValue = "0") String page,
            @RequestParam(defaultValue = "5") String size,
            @RequestParam(defaultValue = "name") String sort
    ){

        PageRequest pp = PageRequest.of(Integer.parseInt(page), Integer.parseInt(size), Sort.by(sort));

        List<TeamDtoOut> teams = teamService.findAll(pp);

        return ResponseEntity.ok(teams);
    }

    @GetMapping("/{id}")
    public Team getTeamById(@PathVariable Long id){
        return teamService.getById(id);
    }
}
