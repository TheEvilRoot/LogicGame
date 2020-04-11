package com.theevilroot.logically.common.mouse.states.impl;

import com.theevilroot.logically.common.math.Vector;
import com.theevilroot.logically.common.mouse.states.IStateful;
import com.theevilroot.logically.common.mouse.states.State;

public class StatefulSelectableView implements IStateful {

    private int state = 0;
    private Vector selectOffset = new Vector(Vector.UNIT);

    @Override
    public boolean is(int mask) {
        return (state & mask) > 0;
    }

    @Override
    public void set(int mask) {
        if (canHaveState(State.fromState(state)))
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

    @Override
    public boolean canHaveState(State state) {
        return true;
    }
}
