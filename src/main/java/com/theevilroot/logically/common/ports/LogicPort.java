package com.theevilroot.logically.common.ports;

import com.theevilroot.logically.core.Resources;
import com.theevilroot.logically.common.elements.LogicElement;
import com.theevilroot.logically.core.math.Vector;
import com.theevilroot.logically.common.mouse.MouseHandler;
import com.theevilroot.logically.common.mouse.MouseTrace;
import com.theevilroot.logically.core.observable.MutableObservable;
import com.theevilroot.logically.core.observable.Observable;
import com.theevilroot.logically.core.observable.impl.ObservableValue;
import com.theevilroot.logically.common.view.impl.BaseView;
import javafx.scene.input.MouseEvent;

public class LogicPort extends BaseView implements MouseHandler {

    private LogicOutputPort connection;

    private final MutableObservable<Boolean> value = new ObservableValue<>(false);
    private final LogicElement parent;

    public LogicPort(LogicElement parent, double x, double y) {
        super(x, y);
        this.parent = parent;
        recalculateSize();
    }

    public LogicPort(LogicElement parent) {
        this(parent, 0, 0);
    }


    public boolean getValue() {
        return value.getValue();
    }

    public void setValue(boolean value) {
        this.value.setValue(value);
    }

    public boolean hasConnection() {
        return connection != null;
    }

    public void haveConnected(LogicOutputPort outputPort) {
        connection = outputPort;
    }

    public void haveDisconnected() {
        connection = null;
        setValue(false);
    }

    public void disconnectIfPresent() {
        if (connection != null) {
            connection.disconnect(this);
        }
    }

    public LogicElement getParent() {
        return parent;
    }

    public Observable<Boolean> getObservableValue() {
        return value;
    }

    public void triggerValue() {
        setValue(!getValue());
    }

    @Override
    public String toString() {
        return getClass().getSimpleName() + "{ value=" + value + " }";
    }


    private void recalculateSize() {
        setSize(Resources.ELEMENT_PORT_RADIUS * 2, Resources.ELEMENT_PORT_RADIUS * 2);
    }

    @Override
    public boolean handle(MouseEvent event, Vector relPos, MouseTrace trace) {
        if (Math.abs(relPos.getMag()) < Resources.ELEMENT_PORT_RADIUS) {
            trace.finish(this);
            return true;
        }
        return false;
    }
}
