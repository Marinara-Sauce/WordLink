package com.marinara.wordlink.model;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Builder
@Getter
@Setter
public class Puzzle {
    private int p_id;
    private String start;
    private String target;
    private String solution;
    private LocalDate date;
}
