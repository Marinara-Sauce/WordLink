package com.marinara.wordlink.controller;

import com.marinara.wordlink.service.PuzzleService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/puzzle")
@AllArgsConstructor
public class PuzzleController {

    private PuzzleService puzzleService;

    /**
     * Fetches the current puzzle from the puzzle service
     * TODO: Delete this
     *
     * @return The current puzzle
     */
    @GetMapping("")
    public ResponseEntity<List<String>> getCurrentPuzzle() {
       return new ResponseEntity<>(puzzleService.getCurrentPuzzle().getSolution(), HttpStatus.OK);
    }

}
