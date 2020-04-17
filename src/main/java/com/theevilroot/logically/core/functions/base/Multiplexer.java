package com.theevilroot.logically.core.functions.base;

import com.theevilroot.logically.core.functions.BaseLogicFunction;

public class Multiplexer extends BaseLogicFunction {

    private final int valuePortCount;
    private final int controlPortCount;

    public Multiplexer(int valueInputCount) {
        super(valueInputCount + (int) (Math.log(valueInputCount) / Math.log(2)), 1);
        this.valuePortCount = valueInputCount;
        this.controlPortCount = (int) (Math.log(valueInputCount) / Math.log(2));
    }

    public int getValuePortCount() {
        return valuePortCount;
    }

    public int getControlPortCount() {
        return controlPortCount;
    }

    private int mapValuePort(int index) {
        return index;
    }

    private int mapControlPortIndex(int index) {
        return index + valuePortCount;
    }

    @Override
    public boolean f(int outputIndex, Boolean[] inputValues) {
        int valuePortIndex = 0;
        for (int i = 0; i < getControlPortCount(); i++) {
            valuePortIndex <<= 1;
            if (inputValues[mapControlPortIndex(i)])
                valuePortIndex |= 1;
        }
        return inputValues[mapValuePort(valuePortIndex)];
    }
}
