package com.theevilroot.logically.common.elements.base;

import com.theevilroot.logically.common.elements.LogicElement;
import com.theevilroot.logically.common.ports.LogicPort;

public class LogicMultiplexer extends LogicElement {

    private final int valuePortCount;
    private final int controlPortCount;

    public LogicMultiplexer(double x, double y, int valueInputCount) {
        super(x, y, valueInputCount + (int) (Math.log(valueInputCount) / Math.log(2)) + 1, 1);
        this.valuePortCount = valueInputCount;
        this.controlPortCount = (int) (Math.log(valueInputCount) / Math.log(2)) + 1;
    }

    public LogicMultiplexer(int valueInputCount) {
        this(0, 0, valueInputCount);
    }

    public int getValuePortCount() {
        return valuePortCount;
    }

    public int getControlPortCount() {
        return controlPortCount;
    }

    protected int mapValuePort(int index) {
        return index;
    }

    protected int mapControlPortIndex(int index) {
        return index + valuePortCount;
    }

    @Override
    protected boolean f(int outputIndex, Boolean[] inputValues) {
        int valuePortIndex = 0;
        for (int i = 0; i < getControlPortCount(); i++) {
            valuePortIndex <<= 1;
            if (inputValues[mapControlPortIndex(i)])
                valuePortIndex |= 1;
        }
        return inputValues[mapValuePort(valuePortIndex)];
    }
}
