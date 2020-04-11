package com.theevilroot.logically.gui.drawable;

public interface IDrawable {

    boolean shouldBeRedrawn();

    void setDirty();

    void resetDirty();

    void draw();

}
