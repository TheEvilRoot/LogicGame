package com.theevilroot.logically.common.elements;

import com.theevilroot.logically.core.functions.LogicFunction;
import com.theevilroot.logically.core.Resources;
import com.theevilroot.logically.core.math.Vector;
import com.theevilroot.logically.common.mouse.MouseHandler;
import com.theevilroot.logically.common.mouse.MouseTrace;
import com.theevilroot.logically.core.observable.Observer;
import com.theevilroot.logically.common.ports.LogicOutputPort;
import com.theevilroot.logically.common.ports.LogicPort;
import com.theevilroot.logically.common.view.impl.BaseView;
import javafx.scene.input.MouseEvent;

import java.util.ArrayList;
import java.util.List;

public class LogicElement extends BaseView implements Observer<Boolean>, MouseHandler {

    private final LogicFunction function;

    protected ArrayList<LogicPort> inputPorts;
    protected ArrayList<LogicOutputPort> outputPorts;

    public LogicElement(double x, double y, LogicFunction function) {
        this.function = function;
        recalculateSize();
        initPorts();
    }

    private void initPorts() {
        this.inputPorts = new ArrayList<>();
        this.outputPorts = new ArrayList<>();

        for (int i = 0; i < getInputCount(); i++) {
            LogicPort port = new LogicPort(this);
            inputPorts.add(port);
        }
        for(int i = 0; i < getOutputCount(); i++) {
            outputPorts.add(new LogicOutputPort(this));
        }

        for (LogicPort p : inputPorts) {
            p.getObservableValue().subscribe(this);
        }

        updatePortPositions();
    }

    private void updatePortPositions() {
        if (function != null) {
            double inDelta = getSize().getY() / (getInputCount() + 1);
            double outDelta = getSize().getY() / (getOutputCount() + 1);

            for (int i = 0; i < getInputCount(); i++) {
                inputPorts.get(i).setPosition(getPosition().getX(), getPosition().getY() + (i + 1) * inDelta);
            }
            for (int i = 0; i < getOutputCount(); i++) {
                outputPorts.get(i).setPosition(getPosition().getX() + getSize().getX() - Resources.ELEMENT_PORT_RADIUS * 2,
                        getPosition().getY() + outDelta * (i + 1));
            }
        }
    }

    public int getOutputCount() {
        return function.getOutputCount();
    }

    public int getInputCount() {
        return function.getInputCount();
    }

    public List<LogicPort> getInputPorts() {
        return inputPorts;
    }

    public List<LogicOutputPort> getOutputPorts() {
        return outputPorts;
    }

    public LogicFunction getFunction() {
        return function;
    }

    public void connectPort(int outputIndex, LogicElement element, int inputIndex) {
        this.outputPorts.get(outputIndex).connect(element.inputPorts.get(inputIndex));
        update();
    }

    public void update() {
        for (int i = 0; i < getOutputCount(); i++) {
            outputPorts.get(i).setValue(function.f(i, inputPorts.stream().map(LogicPort::getValue).toArray(Boolean[]::new)));
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
        int portCount = Math.max(getInputCount(), getOutputCount());
        setSize(Resources.ELEMENT_WIDTH_UNIT, (portCount + 1) * (Resources.ELEMENT_HEIGHT_PER_WIRE ));
    }

    @Override
    public boolean handle(MouseEvent event, Vector relPos, MouseTrace trace) {
        if (relPos.getX() >= 0 && relPos.getY() >= 0 && relPos.getX() < getSize().getX() && relPos.getY() < getSize().getY()) {
            trace.addTrace(this);
            for (LogicPort p : inputPorts) {
                Vector relMouse = Vector.minus(relPos, Vector.minus(p.getPosition(), getPosition()));
                relMouse.subtract(new Vector(Resources.ELEMENT_PORT_RADIUS, 0));
                if (p.handle(event, relMouse, trace))
                    return true;
            }
            for (LogicOutputPort p : outputPorts) {
                Vector relMouse = Vector.minus(relPos, Vector.minus(p.getPosition(), getPosition()));
                relMouse.subtract(new Vector(Resources.ELEMENT_PORT_RADIUS, 0));
                if (p.handle(event, relMouse, trace))
                    return true;
            }
            trace.finish(this);
            return true;
        }
        return false;
    }

    @Override
    public void setPosition(Vector fromVector) {
        super.setPosition(fromVector);
        updatePortPositions();
    }

    @Override
    public void setPosition(double x, double y) {
        super.setPosition(x, y);
        updatePortPositions();
    }

}
