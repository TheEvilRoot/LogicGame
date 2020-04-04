package com.theevilroot.logically.common.view.drawers.impl;

import com.theevilroot.logically.common.elements.LogicElement;
import com.theevilroot.logically.common.mouse.selection.State;
import com.theevilroot.logically.common.view.drawers.factory.IDrawerFactory;
import com.theevilroot.logically.common.Resources;
import com.theevilroot.logically.common.view.drawers.IDrawer;
import com.theevilroot.logically.common.math.Vector;
import com.theevilroot.logically.common.ports.LogicOutputPort;
import com.theevilroot.logically.common.ports.LogicPort;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.shape.StrokeLineCap;
import javafx.scene.shape.StrokeLineJoin;

public class LogicElementDrawer implements IDrawer<LogicElement> {

    @Override
    public void drawElement(IDrawerFactory factory, GraphicsContext gc, Canvas cv, LogicElement view) {
        Vector xOffsets = getXOffset(view);
        Vector yOffsets = getYOffset(view);

        // Draw ~the blue~ box
        // Translate info start of box
        gc.translate(view.getPosition().getX(), view.getPosition().getY());

        if (view.getState() == State.SELECTED) {
            gc.setStroke(Resources.ELEMENT_SELECTION_BOX_COLOR);
            gc.setLineWidth(Resources.ELEMENT_SELECTION_BOX_WIDTH);
            gc.strokeRect(0, 0, view.getSize().getX(), view.getSize().getY() + 0);
        }

        gc.translate(xOffsets.getX(), yOffsets.getX());

        gc.setLineWidth(Resources.getElementStrokeWidth(view.getState()));
        gc.setFill(Resources.getElementBackgroundColor(view.getState()));
        gc.setStroke(Resources.getElementStrokeColor(view.getState()));

        // When I write this, only me and god know what the fuck is that
        // Now, only god do.
        // Good luck
        Vector boxSize = Vector.minus(Vector.minus(view.getSize(),
                new Vector(xOffsets.xySum(), yOffsets.xySum())),
                new Vector(Resources.getElementStrokeWidth(view.getState()) * 2,
                        Resources.getElementStrokeWidth(view.getState()) * 2));

        gc.fillRect(Resources.getElementStrokeWidth(view.getState()),
                Resources.getElementStrokeWidth(view.getState()) ,
                boxSize.getX(),
                boxSize.getY()) ;

        gc.setLineJoin(StrokeLineJoin.BEVEL);
        gc.setLineCap(StrokeLineCap.ROUND);
        gc.strokeRect(Resources.getElementStrokeWidth(view.getState()) / 2,
               Resources.getElementStrokeWidth(view.getState()) / 2,
               boxSize.getX() + Resources.getElementStrokeWidth(view.getState()),
                boxSize.getY() + Resources.getElementStrokeWidth(view.getState()));

        gc.restore();
        gc.save(); // Coo-rds on circuit

        IDrawer<LogicPort> portsDrawer = (IDrawer<LogicPort>) factory.getDrawerFor(LogicPort.class);
        if (portsDrawer != null) {
            view.getInputPorts().forEach(p -> {
                gc.save();
                portsDrawer.drawElement(factory, gc, cv, p);
                gc.restore();
            });
        } else throw new RuntimeException("port");

        IDrawer<LogicPort> outPortDrawer = (IDrawer<LogicPort>) factory.getDrawerFor(LogicOutputPort.class);
        if (outPortDrawer != null) {
            view.getOutputPorts().forEach(p -> {
                gc.save();
                outPortDrawer.drawElement(factory, gc, cv, p);
                gc.restore();
            });
        } else throw new RuntimeException("outport");

        gc.restore();

    }

    private Vector getXOffset(LogicElement e) {
        return new Vector(
                e.getInputCount() > 0 ? Resources.ELEMENT_PORT_RADIUS : 0,
                e.getOutputCount() > 0 ? Resources.ELEMENT_PORT_RADIUS : 0
        );
    }

    private Vector getYOffset(LogicElement e){
        return Vector.UNIT;
    }

}
