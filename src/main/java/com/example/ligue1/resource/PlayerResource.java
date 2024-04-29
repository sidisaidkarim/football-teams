package com.example.ligue1.resource;

import com.example.ligue1.dto.in.PlayerDtoIN;
import com.example.ligue1.dto.out.PlayerDtoOut;
import com.example.ligue1.model.Player;
import com.example.ligue1.service.PlayerService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/player")
@Slf4j
public class PlayerResource {


    private  final PlayerService playerService;

    public PlayerResource(PlayerService playerService) {
        this.playerService = playerService;
    }

    @GetMapping("")
    public List<PlayerDtoOut> getAll(){

        return playerService.getAll();
    }

    @PostMapping("")
    public ResponseEntity<PlayerDtoOut> saveNewPlayer(@RequestBody PlayerDtoIN newPlayer){

        PlayerDtoOut player = playerService.addPlayer(newPlayer);
        return ResponseEntity.ok(player);
    }

    @GetMapping("/{id}")
    public ResponseEntity<PlayerDtoOut> getPlayerById(@PathVariable String id){

        return ResponseEntity.ok(playerService.getById(id));

    }
}
