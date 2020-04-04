package com.theevilroot.logically.common.elements;

import com.theevilroot.logically.common.mouse.selection.State;
import com.theevilroot.logically.common.ports.LogicOutputPort;

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
    public void update() {
        outputPorts.forEach(LogicOutputPort::updateConnections);
    }

    @Override
    protected boolean f(int outputIndex, Boolean[] inputValues) {
        return false;
    }

    @Override
    public boolean canHaveState(State state) {
        return !(state == State.HOVER);
    }
}
