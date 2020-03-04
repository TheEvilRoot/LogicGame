package com.theevilroot.logically.gui.drawable;

import javafx.animation.AnimationTimer;

public class DrawableTimer extends AnimationTimer {

    private final IDrawable drawable;

    public DrawableTimer(IDrawable drawable) {
        this.drawable = drawable;
    }

    @Override
    public void handle(long l) {
        if (drawable.shouldBeRedrawn()) {
            drawable.draw();
            drawable.resetDirty();
        }
    }
}
