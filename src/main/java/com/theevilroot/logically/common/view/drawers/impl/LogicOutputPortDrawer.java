package com.theevilroot.logically.common.view.drawers.impl;

import com.theevilroot.logically.common.math.Vector;
import com.theevilroot.logically.common.view.drawers.factory.IDrawerFactory;
import com.theevilroot.logically.common.Resources;
import com.theevilroot.logically.common.view.drawers.IDrawer;
import com.theevilroot.logically.common.ports.LogicOutputPort;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;

public class LogicOutputPortDrawer implements IDrawer<LogicOutputPort> {

    @Override
    public void drawElement(IDrawerFactory factory, GraphicsContext gc, Canvas cv, LogicOutputPort view) {
        gc.setFill(Resources.ELEMENT_PORT_COLOR);
        gc.fillOval(view.getPosition().getX(),
                view.getPosition().getY() - Resources.ELEMENT_PORT_RADIUS, view.getSize().getX(), view.getSize().getY());
        gc.setFill(view.getValue() ? Resources.PORT_ACTIVE_COLOR : Resources.PORT_INACTIVE_COLOR);
        gc.fillOval(view.getPosition().getX() + Resources.ELEMENT_PORT_RADIUS - 4, view.getPosition().getY() - 4, 8, 8);

        gc.setLineWidth(2f);
        gc.setStroke(view.getValue() ? Resources.PORT_ACTIVE_COLOR : Resources.PORT_INACTIVE_COLOR);

        view.getConnections().forEach(c -> {
            connection(gc, view.getPosition(), c.getPosition(), new Vector(view.getPosition().getX(), c.getPosition().getY()));
        });
    }

    private void connection(GraphicsContext gc, Vector from, Vector to, Vector median) {
        gc.beginPath();
        gc.bezierCurveTo(from.getX() + Resources.ELEMENT_PORT_RADIUS,
                from.getY(),
                median.getX(),
                median.getY(),
                to.getX() + Resources.ELEMENT_PORT_RADIUS,
                to.getY());
        gc.stroke();
        gc.closePath();
    }

}
