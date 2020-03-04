package com.theevilroot.logically.gui.views;

import com.theevilroot.logically.common.elements.LogicElement;
import com.theevilroot.logically.common.observe.Observer;
import com.theevilroot.logically.common.ports.LogicPort;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

import java.util.ArrayList;

public class LogicElementView extends BaseView implements Observer<Boolean> {

    public static final double elementBoxWidth = 50;
    public static final double elementBoxHeightPerInput = 60;
    public static final double startOffset = LogicPortView.portRadius;
    public static final double endOffset = LogicPortView.portRadius;

    private final LogicElement element;

    private ArrayList<LogicPortView> portViews;

    public LogicElementView(Canvas canvas, double px, double py, LogicElement element) {
        super(canvas, px, py, elementBoxWidth + startOffset + endOffset, element.getInputCount() * (elementBoxHeightPerInput + 1));
        this.element = element;
        this.portViews = new ArrayList<>();

        double inputDelta = (getSize().getY() / (element.getInputCount() + 1));
        double outputDelta = (getSize().getY() / (1 + element.getOutputCount()));

        for (int i = 0; i < element.getInputCount(); i++) {
            LogicPortView portView = new LogicPortView(canvas, startOffset, inputDelta * (i + 1));
            portView.subscribe(this);
            portViews.add(portView);
        }
        for (int i = 0; i < element.getOutputCount(); i++) {
            LogicPortView portView = new LogicPortView(canvas, startOffset + elementBoxWidth, outputDelta * (i + 1));
            portView.subscribe(this);
            portViews.add(portView);
        }
    }

    @Override
    void drawView(GraphicsContext gc) {
        gc.save();
        gc.translate(getPosition().getX() + startOffset, getPosition().getY());
        gc.setFill(Color.BLACK);
        gc.fillRect(0, 0, elementBoxWidth, getSize().getY());
        gc.restore();
        gc.translate(getPosition().getX(), getPosition().getY());
        portViews.forEach(port -> {
            gc.save();
            port.drawView(gc);
            gc.restore();
        });
    }

    @Override
    public void valueUpdated(Boolean oldValue, Boolean newValue) {
        if (newValue)
            setDirty();
    }

    @Override
    public void resetDirty() {
        super.resetDirty();
        portViews.forEach(BaseView::resetDirty);
    }
}
