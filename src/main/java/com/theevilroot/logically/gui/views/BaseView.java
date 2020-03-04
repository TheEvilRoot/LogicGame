package com.theevilroot.logically.gui.views;

import com.theevilroot.logically.common.math.Vector;
import com.theevilroot.logically.common.observe.SimpleObservable;
import com.theevilroot.logically.gui.drawable.IDrawable;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;

public abstract class BaseView extends SimpleObservable<Boolean> implements IDrawable {

    private final Vector position;
    private final Vector size;

    private boolean debugBox = false;
    private boolean shouldRedraw = true;

    private final GraphicsContext gc;

    public BaseView(Canvas canvas, double px, double py, double w, double h) {
        this.gc = canvas.getGraphicsContext2D();
        this.position = new Vector(px, py);
        this.size = new Vector(w, h);
    }

    public Vector getSize() {
        return size;
    }

    public Vector getPosition() {
        return position;
    }

    public void debugBox(boolean show) {
        this.debugBox = show;
    }

    @Override
    public boolean shouldBeRedrawn() {
        return shouldRedraw;
    }

    @Override
    public void setDirty() {
        shouldRedraw = true;
        notify(false, true);
    }

    @Override
    public void resetDirty() {
        shouldRedraw = false;
    }

    @Override
    public void draw() {
        if (debugBox) {
            gc.save();
            gc.translate(position.getX(), position.getY());
            gc.beginPath();
            gc.moveTo(0, 0);
            gc.lineTo(size.getX(), 0);
            gc.lineTo(size.getX(), size.getY());
            gc.lineTo(0, size.getY());
            gc.lineTo(0, 0);
            gc.stroke();
            gc.closePath();
            gc.restore();
        }

        gc.save();
        drawView(gc);
        gc.restore();
    }

    public boolean intersects(Vector point) {
        return point.getX() >= getPosition().getX() && point.getY() >= getPosition().getY() &&
                point.getX() <= getPosition().getX() + getSize().getX() && point.getY() <= getPosition().getY() + getSize().getY();
    }

    abstract void drawView(GraphicsContext gc);
}
