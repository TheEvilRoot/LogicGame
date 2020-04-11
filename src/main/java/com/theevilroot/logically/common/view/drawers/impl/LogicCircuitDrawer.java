package com.theevilroot.logically.common.view.drawers.impl;

import com.theevilroot.logically.common.elements.LogicCircuit;
import com.theevilroot.logically.common.elements.LogicElement;
import com.theevilroot.logically.common.view.drawers.factory.IDrawerFactory;
import com.theevilroot.logically.common.Resources;
import com.theevilroot.logically.common.view.drawers.IDrawer;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;

public class LogicCircuitDrawer implements IDrawer<LogicCircuit> {

    @Override
    public void drawElement(IDrawerFactory factory, GraphicsContext gc, Canvas cv, LogicCircuit view) {
        gc.save();
        gc.translate(view.getPosition().getX(), view.getPosition().getY());

        gc.setFill(Resources.CIRCUIT_BACKGROUND_PRIMARY);
        gc.fillRect(0, 0, view.getSize().getX(), view.getSize().getY());

        double xDelta = view.getSize().getX() / 30;

        gc.setFill(Resources.CIRCUIT_BACKGROUND_SECONDARY);
        for (double a = xDelta; a < view.getSize().getX() || a < view.getSize().getY(); a += xDelta) {
            gc.fillRect(a, 0, 1, view.getSize().getY());
            gc.fillRect(0, a, view.getSize().getX(), 1);
        }

        view.getElements().forEach(e -> {
            IDrawer<? super LogicElement> drawer = factory.getDrawerFor(e.getClass());
            if (drawer != null) {
                gc.save();
                drawer.drawElement(factory, gc, cv, e);
                gc.restore();
            } else throw new RuntimeException(e.getClass().getCanonicalName());
        });

        gc.restore();
    }

}
