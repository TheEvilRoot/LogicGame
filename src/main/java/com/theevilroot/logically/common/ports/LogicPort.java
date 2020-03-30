package com.theevilroot.logically.common.ports;

import com.theevilroot.logically.common.Resources;
import com.theevilroot.logically.common.observe.MutableObservable;
import com.theevilroot.logically.common.observe.Observable;
import com.theevilroot.logically.common.observe.impl.ObservableValue;
import com.theevilroot.logically.common.view.impl.BaseView;

public class LogicPort extends BaseView {

    private MutableObservable<Boolean> value = new ObservableValue<>(false);

    public LogicPort(double x, double y) {
        super(x, y);
        recalculateSize();
    }

    public LogicPort() {
        this(0, 0);
    }


    public boolean getValue() {
        return value.getValue();
    }

    public LogicPort setValue(boolean value) {
        this.value.setValue(value);
        return this;
    }

    public Observable<Boolean> getObservableValue() {
        return value;
    }

    public LogicPort triggerValue() {
        return setValue(!getValue());
    }

    @Override
    public String toString() {
        return "LogicPort{ value=" + value + "}";
    }


    private void recalculateSize() {
        setSize(Resources.ELEMENT_PORT_RADIUS * 2, Resources.ELEMENT_PORT_RADIUS * 2);
    }
}
