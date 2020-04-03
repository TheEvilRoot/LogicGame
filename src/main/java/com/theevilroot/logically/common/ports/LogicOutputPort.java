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
        this.connectedPorts.add(port);
        return this;
    }

    public LogicOutputPort connect(LogicPort ...ports) {
        this.connectedPorts.addAll(Arrays.asList(ports));
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
        connectedPorts.forEach((connection) -> connection.setValue(this.getValue()));
    }

    @Override
    public boolean handle(MouseEvent event, Vector relPos, MouseTrace trace) {
        if (Math.abs(relPos.getMag()) < Resources.ELEMENT_PORT_RADIUS) {
            if (getParent() instanceof LogicInputPanel) {
                triggerValue();
                return true;
            }
            trace.finish(this);
            return true;
        }
        return false;
    }
}
