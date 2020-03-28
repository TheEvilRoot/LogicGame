package com.theevilroot.logically.common.gui.drawers;

import com.theevilroot.logically.common.gui.IDrawer;
import com.theevilroot.logically.common.gui.IDrawerFactory;
import com.theevilroot.logically.common.gui.Resources;
import com.theevilroot.logically.common.ports.LogicPort;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;

public class LogicPortDrawer implements IDrawer<LogicPort> {
    @Override
    public void drawElement(IDrawerFactory factory, GraphicsContext gc, Canvas cv, LogicPort view) {
        gc.save();
        gc.setFill(Resources.ELEMENT_PORT_COLOR);
        gc.fillOval(0, 0, view.getSize().getX(), view.getSize().getY());
        gc.restore();
    }
}
