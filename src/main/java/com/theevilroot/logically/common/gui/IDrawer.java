package com.theevilroot.logically.common.gui;


import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;

public interface IDrawer<VT extends IView> {

    void drawElement(IDrawerFactory factory, GraphicsContext gc, Canvas cv, VT view);

}
