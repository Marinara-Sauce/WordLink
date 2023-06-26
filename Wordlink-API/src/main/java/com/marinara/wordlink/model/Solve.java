package com.marinara.wordlink.model;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Builder
@Getter
@Setter
public class Solve {
    private int s_id;
    private int p_id;
    private LocalDateTime timestamp;
    private int numSteps;
}
