package com.marinara.wordlink.persistence;

import com.marinara.wordlink.generated.tables.records.SolveRecord;
import com.marinara.wordlink.model.Solve;
import lombok.AllArgsConstructor;
import org.jooq.DSLContext;
import org.springframework.stereotype.Repository;

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
}
