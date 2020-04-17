package com.theevilroot.logically.gui.platform;

import com.theevilroot.logically.common.elements.LogicElement;
import com.theevilroot.logically.core.math.Vector;
import com.theevilroot.logically.common.mouse.MouseTrace;
import com.theevilroot.logically.common.view.drawers.factory.IDrawerFactory;
import com.theevilroot.logically.common.ui.UIView;
import com.theevilroot.logically.gui.platform.handlers.PlatformCircuitHandler;
import com.theevilroot.logically.gui.platform.handlers.PlatformUIHandler;
import com.theevilroot.logically.gui.drawable.impl.SimpleDrawablePane;
import javafx.beans.InvalidationListener;
import javafx.event.EventHandler;
import javafx.scene.canvas.Canvas;
import javafx.scene.input.MouseEvent;


public class PlatformPane extends SimpleDrawablePane implements EventHandler<MouseEvent>, IPlatform {

    private final Canvas canvas;

    private final PlatformCircuitHandler circuitHandler;
    private final PlatformUIHandler uiHandler;

    private final IDrawerFactory drawerFactory;

    private final Vector mouse = new Vector(Vector.UNIT);

    public PlatformPane(IDrawerFactory drawerFactory) {
        this.circuitHandler = new PlatformCircuitHandler(this);
        this.uiHandler = new PlatformUIHandler(this);
        this.drawerFactory = drawerFactory;
        this.canvas = new Canvas();

        initCanvas();
    }

    private void initCanvas() {
        canvas.widthProperty().bind(this.widthProperty());
        canvas.heightProperty().bind(this.heightProperty());

        InvalidationListener canvasResizeListener = (e) ->
                circuitHandler.sizeUpdated(canvas.getWidth(), canvas.getHeight());

        canvas.widthProperty().addListener(canvasResizeListener);
        canvas.heightProperty().addListener(canvasResizeListener);

        canvas.setOnMouseMoved(e ->
                mouse.set(e.getX(), e.getY()));

        canvas.setOnMouseClicked(this);

        getChildren().add(canvas);
        drawingTimer.start();
    }

    public void draw() {
        circuitHandler.draw();
        uiHandler.draw();
    }

    @Override
    public boolean shouldBeRedrawn() {
        return super.shouldBeRedrawn() || uiHandler.shouldBeRedrawn() || circuitHandler.shouldBeRedrawn();
    }

    @Override
    public void resetDirty() {
        super.resetDirty();
        uiHandler.resetDirty();
        circuitHandler.resetDirty();
    }

    @Override
    public void handle(MouseEvent mouseEvent) {
        Vector absMouse = new Vector(mouseEvent.getX(), mouseEvent.getY());
        MouseTrace trace = new MouseTrace();

        if (uiHandler.handle(mouseEvent, absMouse, trace))
            return;
        circuitHandler.handle(mouseEvent, absMouse, trace);
        setDirty();
    }

    @Override
    public Canvas getCanvas() {
        return canvas;
    }

    @Override
    public Vector getMouse() {
        return mouse;
    }

    @Override
    public IDrawerFactory getDrawerFactory() {
        return drawerFactory;
    }

    @Override
    public void addElement(LogicElement element) {
        circuitHandler.addElement(element);
    }

    @Override
    public void addView(UIView view) {
        uiHandler.addView(view);
    }
}
