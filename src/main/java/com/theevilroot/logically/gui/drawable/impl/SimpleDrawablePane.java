package com.theevilroot.logically.gui.drawable.impl;

import com.theevilroot.logically.gui.drawable.DrawableTimer;
import com.theevilroot.logically.gui.drawable.IDrawable;
import javafx.animation.AnimationTimer;
import javafx.scene.layout.Pane;

public abstract class SimpleDrawablePane extends Pane implements IDrawable {

    private boolean dirty;

    protected final AnimationTimer drawingTimer;

    public SimpleDrawablePane() {
        this.dirty = false;
        this.drawingTimer = new DrawableTimer(this);
    }

    @Override
    public boolean shouldBeRedrawn() {
        return dirty;
    }

    @Override
    public void setDirty() {
        dirty = true;
    }

    @Override
    public void resetDirty() {
        dirty = false;
    }

    @Override
    public abstract void draw();
}
