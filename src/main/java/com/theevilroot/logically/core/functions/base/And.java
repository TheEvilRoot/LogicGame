package com.theevilroot.logically.core.functions.base;

import com.theevilroot.logically.core.functions.BaseLogicFunction;

public class And extends BaseLogicFunction {

    public And(int inputCount) {
        super(inputCount, 1);
    }

    @Override
    public boolean f(int outputIndex, Boolean[] inputValues) {
        boolean result = true;
        for (Boolean value : inputValues)
            result = value && result;
        return result;
    }
}
