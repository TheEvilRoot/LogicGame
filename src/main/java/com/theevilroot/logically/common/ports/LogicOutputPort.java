package com.theevilroot.logically.common.ports;

import com.theevilroot.logically.common.Resources;
import com.theevilroot.logically.common.elements.LogicElement;
import com.theevilroot.logically.common.elements.LogicInputPanel;
import com.theevilroot.logically.common.math.Vector;
import com.theevilroot.logically.common.mouse.MouseTrace;
import javafx.scene.input.MouseEvent;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class LogicOutputPort extends LogicPort {

    private ArrayList<LogicPort> connectedPorts = new ArrayList<>();

    public LogicOutputPort(LogicElement parent, double x, double y) {
        super(parent, x, y);
    }

    public LogicOutputPort(LogicElement parent) {
        super(parent);
    }

    public LogicOutputPort connect(LogicPort port) {
        if (!this.connectedPorts.contains(port) && !port.hasConnection()) {
            this.connectedPorts.add(port);
            port.haveConnected(this);
            getParent().update();
        }
        return this;
    }

    public LogicOutputPort connect(LogicPort ...ports) {
        for (LogicPort port : ports)
            connect(port);
        return this;
    }

    public LogicOutputPort disconnect(LogicPort port) {
        connectedPorts.remove(port);
        port.haveDisconnected();
        return this;
    }

    @Override
    public LogicPort setValue(boolean value) {
        super.setValue(value);
        updateConnections();
        return this;
    }

    public List<LogicPort> getConnections() {
        return connectedPorts;
    }

    public void updateConnections() {
        connectedPorts.forEach((connection) -> {
            if (connection.getValue() != getValue()) {
                connection.setValue(this.getValue());
            }
        });
    }

    @Override
    public boolean handle(MouseEvent event, Vector relPos, MouseTrace trace) {
        if (Math.abs(relPos.getMag()) < Resources.ELEMENT_PORT_RADIUS) {
            if (getParent() instanceof LogicInputPanel) {
                triggerValue();
            }
            trace.finish(this);
            return true;
        }
        return false;
    }
}
