package com.theevilroot.logically.common.elements.base;

import com.theevilroot.logically.common.elements.LogicElement;

public class LogicOrGate extends LogicElement {

    public LogicOrGate(double x, double y, int inputCount) {
        super(x, y, inputCount, 1);
    }

    public LogicOrGate(int inputCount) {
        super(inputCount, 1);
    }

    @Override
    protected boolean f(int outputIndex, Boolean[] inputValues) {
        boolean result = false;
        for (Boolean value : inputValues)
            result = value || result;
        return result;
    }
}
