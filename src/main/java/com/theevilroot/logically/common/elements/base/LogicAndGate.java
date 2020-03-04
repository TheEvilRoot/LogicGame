package com.theevilroot.logically.common.elements.base;

import com.theevilroot.logically.common.elements.LogicElement;

public class LogicAndGate extends LogicElement {

    public LogicAndGate(int inputCount) {
        super(inputCount, 1);
    }

    @Override
    protected boolean f(int outputIndex, Boolean[] inputValues) {
        boolean result = true;
        for (Boolean value : inputValues)
            result = value && result;
        return result;
    }
}
