package com.theevilroot.logically.common.elements;

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
}
