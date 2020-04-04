package com.theevilroot.logically.common.view.impl;

import com.theevilroot.logically.common.math.Vector;
import com.theevilroot.logically.common.mouse.selection.impl.Stateful;
import com.theevilroot.logically.common.view.IView;

public class BaseView extends Stateful implements IView {

    private Vector position = new Vector(Vector.UNIT);
    private Vector size = new Vector(Vector.UNIT);

    public BaseView(double x, double y) {
        setPosition(x, y);
    }

    public BaseView(double x, double y, double w, double h) {
        this(x, y);
        setSize(w, h);
    }

    public BaseView(Vector pos) {
        setPosition(pos);
    }

    public BaseView(Vector pos, Vector size) {
        this(pos);
        setSize(size);
    }

    public BaseView() {
        this(0, 0, 0, 0);
    }

    public void setPosition(double x, double y) {
        position.set(x, y);
    }

    public void setPosition(Vector fromVector) {
        setPosition(fromVector.getX(), fromVector.getY());
    }

    protected void setSize(double x, double y) {
        size.set(x, y);
    }

    protected void setSize(Vector fromVector) {
        setSize(fromVector.getX(), fromVector.getY());
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
