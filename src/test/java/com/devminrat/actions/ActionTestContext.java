package com.devminrat.actions;

public class ActionTestContext {
    public static TestContext.Spec tc(TestContext.Spec spec) {
        return spec.foodCount(5)
                .predatorsCount(5)
                .herbivoresCount(5);
    }
}
