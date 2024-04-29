package com.example.ligue1.dto.in;

import com.example.ligue1.model.Player;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class TeamDtoIn {
    private String name;
    private String acronym;
    private Integer budget;
    private List<Player> players;
}
