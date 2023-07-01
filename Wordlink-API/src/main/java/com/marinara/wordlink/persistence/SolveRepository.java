package com.marinara.wordlink.persistence;

import com.marinara.wordlink.generated.tables.records.SolveRecord;
import com.marinara.wordlink.model.PriorPuzzle;
import com.marinara.wordlink.model.Solve;
import lombok.AllArgsConstructor;
import org.jooq.DSLContext;
import org.jooq.Result;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.stream.Collectors;

import static com.marinara.wordlink.generated.tables.Solve.SOLVE;

@Repository
@AllArgsConstructor
public class SolveRepository {

    private DSLContext dsl;

    public void storeSolve(Solve solve) {
        SolveRecord rec = new SolveRecord();
        rec.from(solve);

        dsl.insertInto(SOLVE).set(rec).execute();
    }

    public PriorPuzzle getStatsForPuzzle(int p_id) {
        // TODO: Investigate making this better with the magic of SQL
        Result<SolveRecord> solves = dsl.selectFrom(SOLVE)
                .where(SOLVE.P_ID.eq(p_id))
                .fetch();

        List<Integer> allSolves = solves.stream().map(SolveRecord::getNumSteps).collect(Collectors.toList());

        return PriorPuzzle.builder()
                .bestSolve(getMin(allSolves))
                .avgSteps(getAverage(allSolves))
                .numSolves(allSolves.size())
                .build();
    }

    private double getAverage(List<Integer> list) {
        double total = 0;

        for (int i : list) {
            total += i;
        }

        return total / list.size();
    }

    private int getMin(List<Integer> list) {
        int min = Integer.MAX_VALUE;

        for (int i : list) {
            if (i < min) {
                min = i;
            }
        }

        return min;
    }
}
