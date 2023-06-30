package com.marinara.wordlink.persistence;

import com.marinara.wordlink.generated.tables.records.PuzzleRecord;
import com.marinara.wordlink.model.Puzzle;
import lombok.AllArgsConstructor;
import org.jooq.DSLContext;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.Optional;

import static com.marinara.wordlink.generated.tables.Puzzle.PUZZLE;

@Repository
@AllArgsConstructor
public class PuzzleRepository {

    private DSLContext dsl;

    public Puzzle getPuzzle(int id) {
        Optional<PuzzleRecord> record = dsl.selectFrom(PUZZLE)
                .where(PUZZLE.P_ID.eq(id))
                .fetchInto(PuzzleRecord.class)
                .stream()
                .findFirst();

        return null;
    }

    public Puzzle getPuzzleOnDay(LocalDate date) {
        Optional<PuzzleRecord> record = dsl.selectFrom(PUZZLE)
                .where(PUZZLE.DATE.eq(date))
                .fetchInto(PuzzleRecord.class)
                .stream()
                .findFirst();

        return record.map(puzzleRecord -> Puzzle.builder()
                .date(puzzleRecord.getDate())
                .start(puzzleRecord.getStart())
                .target(puzzleRecord.getTarget())
                .solution(puzzleRecord.getSolution())
                .p_id(puzzleRecord.getPId())
                .build()).orElse(null);
    }

    public void storePuzzle(Puzzle puzzle) {
        PuzzleRecord pr = new PuzzleRecord();
        pr.from(puzzle);

        PuzzleRecord res = dsl.insertInto(PUZZLE).set(pr).returning(PUZZLE.P_ID).fetchOne();
        puzzle.setP_id(res.getPId());
    }
}
