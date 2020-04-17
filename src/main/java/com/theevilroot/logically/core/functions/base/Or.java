package com.theevilroot.logically.core.functions.base;

import com.theevilroot.logically.core.functions.BaseLogicFunction;

public class Or extends BaseLogicFunction {

    public Or(int inputCount) {
        super(inputCount, 1);
    }

    @Override
    public boolean f(int outputIndex, Boolean[] inputValues) {
        boolean result = false;
        for (Boolean value : inputValues)
            result = value || result;
        return result;
    }
}
