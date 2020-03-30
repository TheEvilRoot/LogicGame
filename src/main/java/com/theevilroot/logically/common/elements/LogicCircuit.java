package com.theevilroot.logically.common.elements;

import com.theevilroot.logically.common.mouse.MouseHandler;
import com.theevilroot.logically.common.view.IView;
import com.theevilroot.logically.common.math.Vector;
import com.theevilroot.logically.common.view.impl.BaseView;
import javafx.scene.input.MouseEvent;

import java.util.ArrayList;
import java.util.List;

public class LogicCircuit extends BaseView implements MouseHandler {

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

    public void setSize(double w, double h) {
        super.setSize(w, h);
    }

    @Override
    public String toString() {
        return "LogicCircuit";
    }

    @Override
    public boolean handle(MouseEvent event, Vector relPos) {
        if (relPos.getX() >= 0 && relPos.getY() >= 0 && relPos.getX() < getSize().getX() && relPos.getY() < getSize().getY()) {
            System.out.println(this);
            for (LogicElement e : elements) {
                Vector relMouse = Vector.minus(relPos, e.getPosition());
                if (e.handle(event, relMouse))
                    return true;
            }
        }
        return false;
    }
}
