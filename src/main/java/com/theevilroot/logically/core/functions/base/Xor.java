package com.theevilroot.logically.core.functions.base;

import com.theevilroot.logically.core.functions.BaseLogicFunction;

public class Xor extends BaseLogicFunction {
    public Xor() {
        super(2, 1);
    }

    @Override
    public boolean f(int outputIndex, Boolean[] inputValues) {
        return inputValues[0] != inputValues[1];
    }
}
