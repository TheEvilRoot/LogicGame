package com.theevilroot.logically.common.elements;

import com.theevilroot.logically.common.Resources;
import com.theevilroot.logically.common.math.Vector;
import com.theevilroot.logically.common.mouse.MouseHandler;
import com.theevilroot.logically.common.observe.Observer;
import com.theevilroot.logically.common.ports.LogicOutputPort;
import com.theevilroot.logically.common.ports.LogicPort;
import com.theevilroot.logically.common.view.impl.BaseView;
import javafx.scene.input.MouseEvent;

import java.util.ArrayList;
import java.util.List;

public abstract class LogicElement extends BaseView implements Observer<Boolean>, MouseHandler {

    private final int inputCount;
    private final int outputCount;

    protected ArrayList<LogicPort> inputPorts;
    protected ArrayList<LogicOutputPort> outputPorts;

    public LogicElement(int inputCount, int outputCount) {
        this(0, 0, inputCount, outputCount);
    }

    public LogicElement(double x, double y, int inputCount, int outputCount) {
        super(x, y);
        this.inputCount = inputCount;
        this.outputCount = outputCount;

        recalculateSize();
        initPorts();
    }

    private void initPorts() {
        this.inputPorts = new ArrayList<>();
        this.outputPorts = new ArrayList<>();

        double inDelta = getSize().getY() / (inputCount + 1);
        double outDelta = getSize().getY() / (outputCount + 1);

        for (int i = 0; i < inputCount; i++) {
            LogicPort port = new LogicPort(getPosition().getX(), getPosition().getY() + (i + 1) * inDelta);
            inputPorts.add(port);
        }
        for(int i = 0; i < outputCount; i++) {
            outputPorts.add(new LogicOutputPort(getPosition().getX() + getSize().getX() - Resources.ELEMENT_PORT_RADIUS * 2,
                    getPosition().getY() + outDelta * (i + 1)));
        }

        for (LogicPort p : inputPorts) {
            p.getObservableValue().subscribe(this);
        }
    }

    public int getOutputCount() {
        return outputCount;
    }

    public int getInputCount() {
        return inputCount;
    }

    public List<LogicPort> getInputPorts() {
        return inputPorts;
    }

    public List<LogicOutputPort> getOutputPorts() {
        return outputPorts;
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
    public void valueUpdated(Boolean newValue) {
        update();
    }

    @Override
    public String toString() {
        return getClass().getSimpleName();
    }

    protected void recalculateSize() {
        setSize(Resources.ELEMENT_WIDTH_UNIT, getInputCount() * (Resources.ELEMENT_HEIGHT_PER_WIRE + 1));
    }

    @Override
    public boolean handle(MouseEvent event, Vector relPos) {
        if (relPos.getX() >= 0 && relPos.getY() >= 0 && relPos.getX() < getSize().getX() && relPos.getY() < getSize().getY()) {
            System.out.println(this);
            for (LogicPort p : inputPorts) {
                Vector relMouse = Vector.minus(relPos, Vector.minus(p.getPosition(), getPosition()));
                relMouse.subtract(new Vector(Resources.ELEMENT_PORT_RADIUS, 0));
                if (p.handle(event, relMouse))
                    return true;
            }
            for (LogicOutputPort p : outputPorts) {
                Vector relMouse = Vector.minus(relPos, Vector.minus(p.getPosition(), getPosition()));
                relMouse.subtract(new Vector(Resources.ELEMENT_PORT_RADIUS, 0));
                if (p.handle(event, relMouse))
                    return true;
            }
            return true;
        }
        return false;
    }

    protected abstract boolean f(int outputIndex, Boolean[] inputValues);

}
