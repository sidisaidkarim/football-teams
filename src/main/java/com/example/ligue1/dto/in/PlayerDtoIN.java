package com.example.ligue1.dto.in;

import com.example.ligue1.enums.POSITION;
import com.example.ligue1.model.Team;
import lombok.Data;

@Data
public class PlayerDtoIN {

    private String name;
    private POSITION position;
    private Long team_id;
}
