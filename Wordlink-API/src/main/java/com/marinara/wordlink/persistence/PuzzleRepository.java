package com.marinara.wordlink.persistence;

import com.marinara.wordlink.generated.tables.Puzzles;
import com.marinara.wordlink.generated.tables.records.PuzzlesRecord;
import com.marinara.wordlink.model.Puzzle;
import lombok.AllArgsConstructor;
import org.jooq.DSLContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Repository
@AllArgsConstructor
public class PuzzleRepository {

    private DSLContext dsl;

    public Puzzle getPuzzle(int id) {
        Optional<PuzzlesRecord> record = dsl.select(Puzzles.PUZZLES.P_ID)
                .from(Puzzles.PUZZLES)
                .where(Puzzles.PUZZLES.P_ID.eq(id))
                .fetchInto(PuzzlesRecord.class)
                .stream()
                .findFirst();

        if (record.isPresent()) {
            PuzzlesRecord p = record.get();
            return new Puzzle(p.getStart(), p.getTarget(), recordSolutionToList(p.getSolution()));
        }

        return null;
    }

    /**
     * Converts the solution in the record (comma seperated) to a list to be stored in the puzzle object
     *
     * @param recordSolution Comma seperated string as stored in the DB
     * @return The list
     */
    private List<String> recordSolutionToList(String recordSolution) {
        return Arrays.stream(recordSolution.split(",")).collect(Collectors.toList());
    }
}
