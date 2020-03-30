package com.theevilroot.logically.common.elements;

public class LogicInputPanel extends LogicElement {

    public LogicInputPanel(double x, double y, int ports) {
        super(x, y, ports, ports);
    }

    public LogicInputPanel(int ports) {
        super(ports, ports);
    }

    public LogicInputPanel triggerPort(int portIndex) {
        portIndex %= getOutputCount();
        inputPorts.get(portIndex).triggerValue();
        return this;
    }

    public LogicInputPanel setValues(boolean ...values) {
        for (int i = 0; i < this.getInputCount() && i < values.length; i++)
            inputPorts.get(i).setValue(values[i]);
        return this;
    }

    @Override
    protected boolean f(int outputIndex, Boolean[] inputValues) {
        return inputValues[outputIndex];
    }

}
