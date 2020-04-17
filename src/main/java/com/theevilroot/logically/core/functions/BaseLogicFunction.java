package com.theevilroot.logically.core.functions;

public abstract class BaseLogicFunction implements LogicFunction {

    public int outputCount;
    public int inputCount;

    public BaseLogicFunction(int inputCount, int outputCount) {
        this.inputCount = inputCount;
        this.outputCount = outputCount;
    }

    @Override
    public int getOutputCount() {
        return outputCount;
    }

    @Override
    public int getInputCount() {
        return inputCount;
    }
}
