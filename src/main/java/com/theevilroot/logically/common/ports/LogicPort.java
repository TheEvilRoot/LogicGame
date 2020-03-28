package com.theevilroot.logically.common.ports;

import com.theevilroot.logically.common.gui.IView;
import com.theevilroot.logically.common.gui.Resources;
import com.theevilroot.logically.common.math.Vector;
import com.theevilroot.logically.common.observe.MutableObservable;
import com.theevilroot.logically.common.observe.Observable;
import com.theevilroot.logically.common.observe.ObservableValue;

public class LogicPort implements IView {

    private MutableObservable<Boolean> value = new ObservableValue<Boolean>(false);
    protected Vector position = new Vector(Vector.UNIT);
    protected Vector size = new Vector(Vector.UNIT);

    public LogicPort(double x, double y, boolean initialValue) {
        this.value.setValue(initialValue);
        this.position.set(x, y);
        recalculateSize();
    }

    public LogicPort(double x, double y) {
        this(x, y, false);
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

    @Override
    public Vector getPosition() {
        return position;
    }

    @Override
    public Vector getSize() {
        return size;
    }

    private void recalculateSize() {
        size.set(Resources.ELEMENT_PORT_RADIUS, Resources.ELEMENT_PORT_RADIUS);
    }
}
