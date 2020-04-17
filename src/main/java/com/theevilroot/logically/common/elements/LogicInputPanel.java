package com.theevilroot.logically.common.elements;

import com.theevilroot.logically.core.functions.base.constant.ConstFalse;
import com.theevilroot.logically.common.ports.LogicOutputPort;

public class LogicInputPanel extends LogicElement {

    public LogicInputPanel(double x, double y, int ports) {
        super(x, y, new ConstFalse(0, ports));
    }

    public void triggerPort(int portIndex) {
        portIndex %= getOutputCount();
        outputPorts.get(portIndex).triggerValue();
    }

    public void setValues(boolean ...values) {
        for (int i = 0; i < this.getOutputCount() && i < values.length; i++)
            outputPorts.get(i).setValue(values[i]);
    }

    @Override
    public void update() {
        outputPorts.forEach(LogicOutputPort::updateConnections);
    }

}
