package com.theevilroot.logically.common.mouse.selection.impl;

import com.theevilroot.logically.common.math.Vector;
import com.theevilroot.logically.common.mouse.selection.ISelectable;
import com.theevilroot.logically.common.mouse.selection.State;

public class Selectable implements ISelectable {

    public static final int SELECTED = 1;
    public static final int HOVER = 2;

    private int state = 0;
    private Vector selectOffset = new Vector(Vector.UNIT);

    @Override
    public boolean is(int mask) {
        return (state & mask) > 0;
    }

    @Override
    public void set(int mask) {
        state |= mask;
    }

    @Override
    public void unset(int mask) {
        state &= ~mask;
    }

    @Override
    public State getState() {
        return State.fromSelectable(this);
    }

    public void setSelectOffset(double x, double y) {
        selectOffset.set(x, y);
    }

    public void setSelectOffset(Vector offset) {
        setSelectOffset(offset.getX(), offset.getY());
    }

    public Vector getSelectOffset() {
        return selectOffset;
    }

}
