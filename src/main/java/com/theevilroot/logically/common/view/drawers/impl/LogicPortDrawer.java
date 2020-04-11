package com.theevilroot.logically.common.view.drawers.impl;

import com.theevilroot.logically.common.math.Vector;
import com.theevilroot.logically.common.mouse.states.impl.StatefulSelectableView;
import com.theevilroot.logically.common.ports.LogicOutputPort;
import com.theevilroot.logically.common.view.drawers.factory.IDrawerFactory;
import com.theevilroot.logically.common.Resources;
import com.theevilroot.logically.common.view.drawers.IDrawer;
import com.theevilroot.logically.common.ports.LogicPort;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;

public class LogicPortDrawer implements IDrawer<LogicPort> {
    @Override
    public void drawElement(IDrawerFactory factory, GraphicsContext gc, Canvas cv, LogicPort view) {
        gc.setFill(Resources.ELEMENT_PORT_COLOR);
        gc.fillOval(view.getPosition().getX(), view.getPosition().getY()
                - Resources.ELEMENT_PORT_RADIUS, view.getSize().getX(), view.getSize().getY());
        gc.setFill(view.getValue() ? Resources.PORT_ACTIVE_COLOR : Resources.PORT_INACTIVE_COLOR);
        gc.fillOval(view.getPosition().getX() + Resources.ELEMENT_PORT_RADIUS - 4, view.getPosition().getY() - 4, 8, 8);

        if (view.is(StatefulSelectableView.SELECTED)) {
            gc.setLineWidth(2f);
            gc.setStroke(view.getValue() ? Resources.PORT_ACTIVE_COLOR : Resources.PORT_INACTIVE_COLOR);
            gc.strokeOval(view.getPosition().getX() - 5,
                    view.getPosition().getY() - Resources.ELEMENT_PORT_RADIUS - 5,
                    view.getSize().getX() + 10,
                    view.getSize().getY() + 10);
        }

        if (view instanceof LogicOutputPort) {
            gc.setLineWidth(2f);
            gc.setStroke(view.getValue() ? Resources.PORT_ACTIVE_COLOR : Resources.PORT_INACTIVE_COLOR);

            ((LogicOutputPort) view).getConnections().forEach(c -> {
                connection(gc, view.getPosition(), c.getPosition(), new Vector(c.getPosition().getX(), view.getPosition().getY()));
            });
        }
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
