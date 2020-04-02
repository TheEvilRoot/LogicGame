package com.theevilroot.logically.common.view.drawers.impl;

import com.theevilroot.logically.common.elements.LogicElement;
import com.theevilroot.logically.common.mouse.selection.ISelectable;
import com.theevilroot.logically.common.mouse.selection.impl.Selectable;
import com.theevilroot.logically.common.view.drawers.factory.IDrawerFactory;
import com.theevilroot.logically.common.Resources;
import com.theevilroot.logically.common.view.drawers.IDrawer;
import com.theevilroot.logically.common.math.Vector;
import com.theevilroot.logically.common.ports.LogicOutputPort;
import com.theevilroot.logically.common.ports.LogicPort;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Paint;

public class LogicElementDrawer implements IDrawer<LogicElement> {

    @Override
    public void drawElement(IDrawerFactory factory, GraphicsContext gc, Canvas cv, LogicElement view) {
        Vector xOffsets = getXOffset(view);
        Vector yOffsets = getYOffset(view);

        // Draw ~the blue~ box
        // Translate info start of box
        gc.translate(view.getPosition().getX() + xOffsets.getX(), view.getPosition().getY() + yOffsets.getX());

        gc.setLineWidth(Resources.getElementStrokeWidth(view.getState()));
        gc.setFill(Resources.getElementBackgroundColor(view.getState()));
        gc.setStroke(Resources.getElementStrokeColor(view.getState()));


        // Draw box with width (width - (start offset + end offset), height - (top offset + bottom offset))
        Vector boxSize = Vector.minus(view.getSize(), new Vector(xOffsets.xySum(), yOffsets.xySum()));
        gc.fillRect(0, 0, boxSize.getX(), boxSize.getY());

        gc.strokeRect(0, 0, boxSize.getX(), boxSize.getY());

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
                e.getOutputCount() > 0 ? Resources.ELEMENT_PORT_RADIUS : 0,
                e.getOutputCount() > 0 ? Resources.ELEMENT_PORT_RADIUS : 0
        );
    }

    private Vector getYOffset(LogicElement e){
        return Vector.UNIT;
    }

}
