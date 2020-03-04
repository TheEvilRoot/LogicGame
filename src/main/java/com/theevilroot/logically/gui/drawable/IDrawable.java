package com.theevilroot.logically.gui.drawable;

import javafx.scene.canvas.GraphicsContext;

public interface IDrawable {

    boolean shouldBeRedrawn();

    void setDirty();

    void resetDirty();

    void draw();

}
