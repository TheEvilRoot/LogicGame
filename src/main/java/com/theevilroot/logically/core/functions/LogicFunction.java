package com.theevilroot.logically.core.functions;

public interface LogicFunction {

    boolean f(int outputIndex, Boolean[] inputValues);

    int getOutputCount();

    int getInputCount();
}
