package com.example.ligue1.dto.out;

import com.example.ligue1.enums.POSITION;
import com.example.ligue1.model.Team;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class PlayerDtoOut {


    private Long id;
    private String name;
    private POSITION position;
    private Team team;
}
