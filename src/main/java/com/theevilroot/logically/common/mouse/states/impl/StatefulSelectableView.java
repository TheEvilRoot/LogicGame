package com.theevilroot.logically.common.mouse.states.impl;

import com.theevilroot.logically.common.math.Vector;
import com.theevilroot.logically.common.mouse.states.IStateful;

public class StatefulSelectableView implements IStateful {

    private int stateMask = 0xFFFFFFFF;
    private int state = 0;
    private final Vector selectOffset = new Vector(Vector.UNIT);

    @Override
    public boolean is(int mask) {
        return (state & mask) > 0;
    }

    @Override
    public void set(int mask) {
        state |= (mask & stateMask);
    }

    @Override
    public void unset(int mask) {
        state &= ~mask;
    }

    @Override
    public boolean can(int mm) {
        return (mm & stateMask) > 0;
    }

    @Override
    public void allow(int mm) {
        stateMask |= mm;
    }

    @Override
    public void deny(int mm) {
        stateMask &= ~mm;
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
