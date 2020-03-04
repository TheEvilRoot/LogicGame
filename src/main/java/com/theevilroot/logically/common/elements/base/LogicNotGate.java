package com.theevilroot.logically.common.elements.base;

import com.theevilroot.logically.common.elements.LogicElement;

public class LogicNotGate extends LogicElement {

    public LogicNotGate() {
        super(1, 1);
    }

    @Override
    protected boolean f(int outputIndex, Boolean[] inputValues) {
        return !inputValues[outputIndex];
    }

}
