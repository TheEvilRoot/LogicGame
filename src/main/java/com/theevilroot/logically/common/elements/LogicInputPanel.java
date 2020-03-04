package com.theevilroot.logically.common.elements;

public class LogicInputPanel extends LogicElement {

    public LogicInputPanel(int ports) {
        super(ports, ports);
    }

    public LogicInputPanel(int ports, boolean ...initialValues) {
        super(ports, ports);
        for (int i = 0; i < ports && i < initialValues.length; i++)
            if (initialValues[i])
                triggerPort(i);
    }

    @Override
    protected boolean f(int outputIndex, Boolean[] inputValues) {
        return inputValues[outputIndex];
    }

    public LogicInputPanel triggerPort(int portIndex) {
        portIndex %= getOutputCount();
        inputPorts.get(portIndex).triggerValue();
        return this;
    }

}
