package com.example.ligue1.integration;


import com.example.ligue1.dto.out.TeamDtoOut;
import com.example.ligue1.enums.POSITION;
import com.example.ligue1.model.Player;
import com.example.ligue1.model.Team;
import com.example.ligue1.repository.PlayerRepository;
import com.example.ligue1.repository.TeamRepository;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.web.client.RestTemplate;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT, properties = {"server.port=" + TeamTest.PORT})
@ActiveProfiles("test")
@Slf4j
public class TeamTest {
    static final int PORT = 55555;

    private final TestRestTemplate restTemplate;
    private final TeamRepository teamRepository;
    private final PlayerRepository playerRepository;

    private ParameterizedTypeReference<List<TeamDtoOut>> responseType = new ParameterizedTypeReference<>() {
    };

    @Autowired
    public TeamTest(TestRestTemplate restTemplate, TeamRepository teamRepository, PlayerRepository playerRepository) {
        this.restTemplate = restTemplate;
        this.teamRepository = teamRepository;
        this.playerRepository = playerRepository;
    }

    @BeforeEach
    public void setUp(){



        Team t1 = Team.builder()
                .name("team1")
                .acronym("tm1")
                .budget(60)
                .build();
        Team t2 = Team.builder()
                .name("team2")
                .acronym("tm2")
                .budget(50)
                .build();

        teamRepository.save(t1);
        teamRepository.save(t2);


        Player player1= Player.builder().name("pl1").position(POSITION.LEFT_WINGER).team(t1).build();
        Player player2= Player.builder().name("pl2").position(POSITION.STRIKER).team(t2).build();

        playerRepository.save(player1);
        playerRepository.save(player2);

    }

    @Test
    public void shouldReturnAllTeams(){
        ResponseEntity<List<TeamDtoOut>> response = restTemplate.exchange("http://localhost:"+PORT+"/api/team",
                HttpMethod.GET, new HttpEntity<>(new HttpHeaders()), responseType);
        assertEquals(200,response.getStatusCode().value());
        List<TeamDtoOut> teams = response.getBody();
        assertEquals(2,teams.size());


    }

    @Test
    public void shouldGetResultSortedByBudget() {

        ResponseEntity<List<TeamDtoOut>> response = restTemplate.exchange("http://localhost:"+PORT+"/api/team?page=0&size=5&sort=budget",
                HttpMethod.GET, new HttpEntity<>(new HttpHeaders()), responseType);
        List<TeamDtoOut> teams = response.getBody();
        log.info("results {}",teams);
        assertEquals(50,teams.get(0).getBudget());

    }

    @Test
    public void shouldGetOnlyOneTeam() {

        ResponseEntity<List<TeamDtoOut>> response = restTemplate.exchange("http://localhost:"+PORT+"/api/team?page=0&size=1&sort=budget",
                HttpMethod.GET, new HttpEntity<>(new HttpHeaders()), responseType);
        List<TeamDtoOut> teams = response.getBody();
        log.info("results length = ",teams.size());
        assertEquals(1,teams.size());

    }
}
