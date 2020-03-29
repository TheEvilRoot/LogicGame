package com.theevilroot.logically.common.gui.drawers;

import com.theevilroot.logically.common.gui.IDrawer;
import com.theevilroot.logically.common.gui.IDrawerFactory;
import com.theevilroot.logically.common.gui.Resources;
import com.theevilroot.logically.common.ports.LogicOutputPort;
import com.theevilroot.logically.common.ports.LogicPort;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;

public class LogicOutputPortDrawer implements IDrawer<LogicOutputPort> {

    @Override
    public void drawElement(IDrawerFactory factory, GraphicsContext gc, Canvas cv, LogicOutputPort view) {
        gc.save();
        gc.setFill(Resources.ELEMENT_PORT_COLOR);
        gc.fillOval(view.getPosition().getX(), view.getPosition().getY() - Resources.ELEMENT_PORT_RADIUS, view.getSize().getX(), view.getSize().getY());
        gc.setFill(view.getValue() ? Resources.PORT_ACTIVE_COLOR : Resources.PORT_INACTIVE_COLOR);
        gc.fillOval(view.getPosition().getX() + Resources.ELEMENT_PORT_RADIUS - 4, view.getPosition().getY() - 4, 8, 8);
        gc.restore();
    }

}
