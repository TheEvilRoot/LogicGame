package com.theevilroot.logically.common.elements;

public class LogicOutputPanel extends LogicElement {

    public LogicOutputPanel(int inputCount) {
        super(inputCount, 0);
    }

    @Override
    protected boolean f(int outputIndex, Boolean[] inputValues) {
        return false;
    }

    public boolean getValue(int index) {
        return inputPorts.get(index).getValue();
    }
}
