package com.theevilroot.logically.core.functions.base;


import com.theevilroot.logically.core.functions.BaseLogicFunction;

public class Not extends BaseLogicFunction {

    public Not(int inputCount) {
        super(inputCount, inputCount);
    }

    @Override
    public boolean f(int outputIndex, Boolean[] inputValues) {
        return !inputValues[outputIndex];
    }
}
