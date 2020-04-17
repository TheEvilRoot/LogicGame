package com.theevilroot.logically.gui.drawable.impl;

import com.theevilroot.logically.gui.drawable.IDrawable;

public abstract class BaseDrawable implements IDrawable {

    private boolean invalidated = false;

    @Override
    public boolean shouldBeRedrawn() {
        return !invalidated;
    }

    @Override
    public void setDirty() {
        invalidated = false;
    }

    @Override
    public void resetDirty() {
        invalidated = true;
    }


}
