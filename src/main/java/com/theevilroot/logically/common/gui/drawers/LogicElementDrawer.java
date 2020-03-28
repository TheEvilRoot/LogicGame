package com.theevilroot.logically.common.gui.drawers;

import com.theevilroot.logically.common.elements.LogicElement;
import com.theevilroot.logically.common.gui.IDrawer;
import com.theevilroot.logically.common.gui.IDrawerFactory;
import com.theevilroot.logically.common.gui.IView;
import com.theevilroot.logically.common.gui.Resources;
import com.theevilroot.logically.common.math.Vector;
import com.theevilroot.logically.common.ports.LogicOutputPort;
import com.theevilroot.logically.common.ports.LogicPort;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

import java.util.List;

public class LogicElementDrawer implements IDrawer<LogicElement> {

    @Override
    public void drawElement(IDrawerFactory factory, GraphicsContext gc, Canvas cv, LogicElement view) {
        Vector xOffsets = getXOffset(view);
        Vector yOffsets = getYOffset(view);

        gc.save();

        // Draw ~the blue~ box
        // Translate info start of box
        gc.translate(view.getPosition().getX() + xOffsets.getX(), view.getPosition().getY() + yOffsets.getX());
        gc.setFill(Color.BLACK);

        // Draw box with width (width - (start offset + end offset), height - (top offset + bottom offset))
        Vector boxSize = Vector.minus(view.getSize(), new Vector(xOffsets.xySum(), yOffsets.xySum()));
        gc.fillRect(0, 0, boxSize.getX(), boxSize.getY());

        gc.restore();
        gc.save();

        IDrawer<IView> portsDrawer = factory.getDrawerFor(LogicPort.class);
        if (portsDrawer != null) {
            view.getInputPorts().forEach(p -> portsDrawer.drawElement(factory, gc, cv, p));
        } else throw new RuntimeException("port");

        IDrawer<IView> outPortDrawer = factory.getDrawerFor(LogicOutputPort.class);
        if (outPortDrawer != null) {
            view.getOutputPorts().forEach(p -> outPortDrawer.drawElement(factory, gc, cv, p));
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
