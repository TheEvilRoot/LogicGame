package com.theevilroot.logically.gui;


import com.theevilroot.logically.common.elements.base.LogicAndGate;
import com.theevilroot.logically.common.observe.Observer;
import com.theevilroot.logically.gui.drawable.SimpleDrawablePane;
import com.theevilroot.logically.gui.views.BaseView;
import com.theevilroot.logically.gui.views.LogicElementView;
import javafx.beans.InvalidationListener;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

import java.util.ArrayList;

public class PlatformPane extends SimpleDrawablePane implements Observer<Boolean> {

    private final Canvas canvas;

    private ArrayList<BaseView> views;

    public PlatformPane() {
        this.views = new ArrayList<>();

        this.canvas = new Canvas();
        canvas.widthProperty().bind(this.widthProperty());
        canvas.heightProperty().bind(this.heightProperty());

        InvalidationListener canvasResizeListener = (e) -> setDirty();
        canvas.widthProperty().addListener(canvasResizeListener);
        canvas.heightProperty().addListener(canvasResizeListener);

        getChildren().add(canvas);

        drawingTimer.start();

        addView(new LogicElementView(canvas, 20, 20, new LogicAndGate(3)));
    }

    public void draw() {
        GraphicsContext gc = canvas.getGraphicsContext2D();

        gc.save();
        gc.setFill(Color.DARKSLATEGRAY);
        gc.fillRect(0, 0, canvas.getWidth(), canvas.getHeight());
        gc.restore();

        views.forEach(BaseView::draw);
    }

    private void addView(BaseView view) {
        view.subscribe(this);
        views.add(view);
    }

    @Override
    public void valueUpdated(Boolean oldValue, Boolean newValue) {
        if (newValue)
            setDirty();
    }

    @Override
    public void resetDirty() {
        super.resetDirty();
        views.forEach(BaseView::resetDirty);
    }
}
