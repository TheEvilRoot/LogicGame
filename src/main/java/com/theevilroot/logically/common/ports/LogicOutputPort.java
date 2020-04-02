package com.theevilroot.logically.common.ports;

import com.theevilroot.logically.common.elements.LogicElement;
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
        connectedPorts.forEach((connection) -> connection.setValue(this.getValue()));
        return this;
    }

    public List<LogicPort> getConnections() {
        return connectedPorts;
    }

}
