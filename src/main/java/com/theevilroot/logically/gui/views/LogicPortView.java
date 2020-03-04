package com.theevilroot.logically.gui.views;

import com.theevilroot.logically.common.math.Vector;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

public class LogicPortView extends BaseView {

    public static final double portRadius = 10;
    public static final double portSize = 20;

    private boolean value = false;

    public LogicPortView(Canvas canvas, double px, double py) {
        super(canvas, px, py, portSize, portSize);
    }

    public boolean getValue() {
        return value;
    }

    public LogicPortView setValue(boolean value) {
        this.value = value;
        notify(false, value);
        return this;
    }

    @Override
    void drawView(GraphicsContext gc) {
        gc.translate(getPosition().getX() - getSize().getX() / 2, getPosition().getY() - getSize().getX() / 2);
        gc.setFill(Color.WHITE);
        gc.fillOval(0, 0, getSize().getX(), getSize().getY());
    }

    @Override
    public boolean intersects(Vector point) {
        double delta = Vector.minus(Vector.plus(getPosition(), Vector.multiply(getSize(), 0.5)), point).abs().getMag();
        return delta <= portRadius;
    }
}
