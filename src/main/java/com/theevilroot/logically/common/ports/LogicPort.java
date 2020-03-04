package com.theevilroot.logically.common.ports;

import com.theevilroot.logically.common.observe.Observable;
import com.theevilroot.logically.common.observe.SimpleObservable;

public class LogicPort extends SimpleObservable<Boolean> {

    private boolean value;

    public LogicPort(boolean initialValue) {
        this.value = initialValue;
    }

    public LogicPort() { this(false); }


    public boolean getValue() {
        return value;
    }

    public LogicPort setValue(boolean value) {
        boolean oldValue = this.value;
        this.value = value;
        notify(oldValue, getValue());
        return this;
    }

    public LogicPort triggerValue() {
        return setValue(!getValue());
    }

    @Override
    public String toString() {
        return "LogicPort{ value=" + value + "}";
    }
}
