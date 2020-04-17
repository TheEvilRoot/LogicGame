package com.theevilroot.logically.common.elements;

import com.theevilroot.logically.core.functions.base.constant.ConstFalse;

public class LogicOutputPanel extends LogicElement {

    public LogicOutputPanel(double x, double y, int inputCount) {
        super(x, y, new ConstFalse(inputCount, 0));
    }

    public boolean getValue(int index) {
        return inputPorts.get(index).getValue();
    }

}
