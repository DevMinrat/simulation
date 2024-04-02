package com.devminrat.actions;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static com.devminrat.actions.ActionTestContext.tc;
import static com.devminrat.utils.Actions.nextTurn;

public class ActionTests {

    private static TestContext tc;

    @BeforeAll
    public static void beforeClass() {
        tc = tc(new TestContext.Spec()).build();

        tc.initTestField();
    }

    @Test
    public void test_nextTurn_predatorCount() throws InterruptedException {
        for (int i = 0; i < 50; i++) {
//            nextTurn(tc.testField, tc.consoleRender);
        }
    }
}
