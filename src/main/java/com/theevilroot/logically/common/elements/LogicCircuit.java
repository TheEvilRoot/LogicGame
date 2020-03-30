package com.theevilroot.logically.common.elements;

import com.theevilroot.logically.common.view.IView;
import com.theevilroot.logically.common.math.Vector;
import com.theevilroot.logically.common.view.impl.BaseView;

import java.util.ArrayList;
import java.util.List;

public class LogicCircuit extends BaseView {

    private ArrayList<LogicElement> elements = new ArrayList<>();

    public LogicCircuit(double x, double y, double width, double height) {
        super(x, y, width, height);
    }

    public void addElement(LogicElement element) {
        this.elements.add(element);
    }

    public List<LogicElement> getElements() {
        return elements;
    }

}
