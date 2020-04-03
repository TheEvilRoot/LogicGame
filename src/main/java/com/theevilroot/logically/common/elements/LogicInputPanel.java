package com.theevilroot.logically.common.elements;

public class LogicInputPanel extends LogicElement {

    public LogicInputPanel(double x, double y, int ports) {
        super(x, y, 0, ports);
    }

    public LogicInputPanel(int ports) {
        super(ports, ports);
    }

    public LogicInputPanel triggerPort(int portIndex) {
        portIndex %= getOutputCount();
        outputPorts.get(portIndex).triggerValue();
        return this;
    }

    public LogicInputPanel setValues(boolean ...values) {
        for (int i = 0; i < this.getOutputCount() && i < values.length; i++)
            outputPorts.get(i).setValue(values[i]);
        return this;
    }

    @Override
    public void update() { }

    @Override
    protected boolean f(int outputIndex, Boolean[] inputValues) {
        return false;
    }

}
