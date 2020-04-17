package com.theevilroot.logically.core.functions.base;


import com.theevilroot.logically.core.functions.BaseLogicFunction;

public class Not extends BaseLogicFunction {

    public Not(int inputCount, int outputCount) {
        super(inputCount, outputCount);
    }

    @Override
    public boolean f(int outputIndex, Boolean[] inputValues) {
        return !inputValues[outputIndex];
    }
}
