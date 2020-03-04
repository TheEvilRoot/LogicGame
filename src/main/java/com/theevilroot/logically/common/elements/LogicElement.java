package com.theevilroot.logically.common.elements;

import com.theevilroot.logically.common.observe.Observer;
import com.theevilroot.logically.common.ports.LogicOutputPort;
import com.theevilroot.logically.common.ports.LogicPort;
import java.util.ArrayList;

public abstract class LogicElement implements Observer<Boolean> {

    private final int inputCount;
    private final int outputCount;

    protected ArrayList<LogicPort> inputPorts;
    protected ArrayList<LogicOutputPort> outputPorts;

    public LogicElement(int inputCount, int outputCount) {
        this.inputCount = inputCount;
        this.outputCount = outputCount;

        this.inputPorts = new ArrayList<>();
        this.outputPorts = new ArrayList<>();

        for (int i = 0; i < inputCount; i++) {
            LogicPort port = new LogicPort();
            port.subscribe(this);
            inputPorts.add(port);
        }
        for(int i = 0; i < outputCount; i++) {
            outputPorts.add(new LogicOutputPort());
        }
    }

    public int getOutputCount() {
        return outputCount;
    }

    public int getInputCount() {
        return inputCount;
    }

    public LogicElement connectPort(int outputIndex, LogicElement element, int inputIndex) {
        this.outputPorts.get(outputIndex).connect(element.inputPorts.get(inputIndex));
        update();
        return this;
    }

    public void update() {
        for (int i = 0; i < getOutputCount(); i++) {
            outputPorts.get(i).setValue(f(i, inputPorts.stream().map(LogicPort::getValue).toArray(Boolean[]::new)));
        }
    }

    @Override
    public void valueUpdated(Boolean oldValue, Boolean newValue) {
        update();
    }

    @Override
    public String toString() {
        return "LogicElement{" +
                "inputCount=" + inputCount +
                ", outputCount=" + outputCount +
                ", inputPorts=" + inputPorts +
                ", outputPorts=" + outputPorts +
                '}';
    }

    protected abstract boolean f(int outputIndex, Boolean[] inputValues);

}
