package com.theevilroot.logically.common.elements;

import com.theevilroot.logically.common.gui.IView;
import com.theevilroot.logically.common.math.Vector;

import java.util.ArrayList;
import java.util.List;

public class LogicCircuit implements IView {

    private Vector size;
    private Vector position;

    private ArrayList<LogicElement> elements = new ArrayList<>();

    public LogicCircuit(double width, double height, double x, double y) {
        size = new Vector(width, height);
        position = new Vector(x, y);
    }

    public void addElement(LogicElement element) {
        this.elements.add(element);
    }

    public List<LogicElement> getElements() {
        return elements;
    }

    @Override
    public Vector getPosition() {
        return position;
    }

    @Override
    public Vector getSize() {
        return size;
    }
}
