package com.theevilroot.logically.common.elements;

import com.theevilroot.logically.common.mouse.selection.State;

public class LogicOutputPanel extends LogicElement {

    public LogicOutputPanel(int inputCount) {
        super(inputCount, 0);
    }

    public LogicOutputPanel(double x, double y, int inputCount) {
        super(x, y, inputCount, 0);
    }

    public boolean getValue(int index) {
        return inputPorts.get(index).getValue();
    }

    @Override
    protected boolean f(int outputIndex, Boolean[] inputValues) {
        return false;
    }

    @Override
    public boolean canHaveState(State state) {
        return true;
    }
}
