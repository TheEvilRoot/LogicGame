package com.theevilroot.logically.common.view.drawers;


import com.theevilroot.logically.common.view.IView;
import com.theevilroot.logically.common.view.drawers.factory.IDrawerFactory;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;

public interface IDrawer<VT extends IView> {

    void drawElement(IDrawerFactory factory, GraphicsContext gc, Canvas cv, VT view);

}
