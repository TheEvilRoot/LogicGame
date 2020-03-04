package com.theevilroot.logically.common.ports;

import java.util.ArrayList;
import java.util.Arrays;

public class LogicOutputPort extends LogicPort {

    private ArrayList<LogicPort> connectedPorts;

    public LogicOutputPort(boolean initialValue) {
        super(initialValue);
        this.connectedPorts = new ArrayList<>();
    }

    public LogicOutputPort() {
        this(false);
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
}
