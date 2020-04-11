package com.theevilroot.logically.common.elements;

import com.theevilroot.logically.common.mouse.MouseHandler;
import com.theevilroot.logically.common.mouse.MouseTrace;
import com.theevilroot.logically.common.math.Vector;
import com.theevilroot.logically.common.mouse.selection.State;
import com.theevilroot.logically.common.mouse.selection.impl.StatefulSelectableView;
import com.theevilroot.logically.common.ports.LogicOutputPort;
import com.theevilroot.logically.common.ports.LogicPort;
import com.theevilroot.logically.common.view.impl.BaseView;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import java.util.ArrayList;
import java.util.List;

public class LogicCircuit extends BaseView implements MouseHandler {

    private ArrayList<LogicElement> elements = new ArrayList<>();

    private LogicElement selectedElement = null;
    private LogicElement hoverElement = null;

    private LogicPort selectedPort = null;

    public LogicCircuit(double x, double y, double width, double height) {
        super(x, y, width, height);
    }

    public void addElement(LogicElement element) {
        this.elements.add(element);
    }

    public List<LogicElement> getElements() {
        return elements;
    }

    public void setSize(double w, double h) {
        super.setSize(w, h);
    }

    @Override
    public String toString() {
        return "LogicCircuit";
    }

    @Override
    public boolean handle(MouseEvent event, Vector relPos, MouseTrace trace) {
        if (relPos.getX() >= 0 && relPos.getY() >= 0 && relPos.getX() < getSize().getX() && relPos.getY() < getSize().getY()) {
            trace.addTrace(this);
            for (LogicElement e : elements) {
                Vector relMouse = Vector.minus(relPos, e.getPosition());
                if (e.handle(event, relMouse, trace))
                    return true;
            }
            trace.finish(this);
            return true;
        }
        return false;
    }

    private void setSelectedElement(LogicElement e, Vector offset) {
        if (e == null || e.canHaveState(State.SELECTED)) {
            selectedElement = e;
            elements.forEach(f -> {
                if (f.is(SELECTED))
                    f.unset(SELECTED);
            });
            if (selectedElement != null) {
                selectedElement.set(SELECTED);
                selectedElement.setSelectOffset(offset);
            }
        }
    }

    private void setHoverElement(LogicElement e, Vector offset) {
        if (e == null || e.canHaveState(State.HOVER)) {
            hoverElement = e;
            elements.forEach(f -> {
                if (f.is(HOVER))
                    f.unset(HOVER);
            });
            if (hoverElement != null) {
                hoverElement.set(HOVER);
                hoverElement.setSelectOffset(offset);
            }
        }
    }

    public void onCircuitClick(MouseEvent mouseEvent, Vector relMouse) {
        if (hoverElement != null)
            setHoverElement(null, Vector.UNIT);
        if (selectedElement != null)
            setSelectedElement(null, Vector.UNIT);
    }

    public void onElementClick(MouseEvent mouseEvent, Vector relMouse, LogicElement element) {
        if (element == selectedElement) {
            if (element == hoverElement) {
                setSelectedElement(null, Vector.UNIT);
                setHoverElement(null, Vector.UNIT);
            } else {
                setHoverElement(element, Vector.minus(relMouse, element.getPosition()));
            }
        } else {
            setSelectedElement(element, Vector.minus(relMouse, element.getPosition()));
            setHoverElement(null, Vector.UNIT);
        }
    }

    public void onElementPortClick(MouseEvent mouseEvent, Vector relMouse, LogicElement lastTrace, LogicPort acceptor) {
        if (mouseEvent.getButton() == MouseButton.SECONDARY) {
            acceptor.disconnectIfPresent();
        } else {
            if (selectedPort == null) {
                selectedPort = acceptor;
                selectedPort.set(StatefulSelectableView.SELECTED);
            } else {
                if (selectedPort instanceof LogicOutputPort && !(acceptor instanceof LogicOutputPort)) {
                    ((LogicOutputPort) selectedPort).connect(acceptor);
                }
                if (acceptor instanceof LogicOutputPort && !(selectedPort instanceof LogicOutputPort)) {
                    ((LogicOutputPort) acceptor).connect(selectedPort);
                }
                selectedPort.unset(StatefulSelectableView.SELECTED);
                selectedPort = null;
            }
        }
    }

    public boolean handleHover(Vector mouse) {
        if (hoverElement != null) {
            hoverElement.setPosition(mouse.getX() - hoverElement.getSelectOffset().getX(),
                    mouse.getY() - hoverElement.getSelectOffset().getY());
            return true;
        }
        return false;
    }
}
