package com.theevilroot.logically.common.ports;

import com.theevilroot.logically.common.Resources;
import com.theevilroot.logically.common.math.Vector;
import com.theevilroot.logically.common.mouse.MouseHandler;
import com.theevilroot.logically.common.observe.MutableObservable;
import com.theevilroot.logically.common.observe.Observable;
import com.theevilroot.logically.common.observe.impl.ObservableValue;
import com.theevilroot.logically.common.view.impl.BaseView;
import javafx.scene.input.MouseEvent;

public class LogicPort extends BaseView implements MouseHandler {

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
        return getClass().getSimpleName() + "{ value=" + value + " }";
    }


    private void recalculateSize() {
        setSize(Resources.ELEMENT_PORT_RADIUS * 2, Resources.ELEMENT_PORT_RADIUS * 2);
    }

    @Override
    public boolean handle(MouseEvent event, Vector relPos) {
        if (Math.abs(relPos.getMag()) < Resources.ELEMENT_PORT_RADIUS) {
            System.out.println(this);
            return true;
        }
        return false;
    }
}
