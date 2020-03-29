package com.theevilroot.logically.gui;


import com.theevilroot.logically.common.elements.LogicCircuit;
import com.theevilroot.logically.common.elements.base.LogicAndGate;
import com.theevilroot.logically.common.gui.IDrawer;
import com.theevilroot.logically.common.gui.IDrawerFactory;
import com.theevilroot.logically.common.gui.IView;
import com.theevilroot.logically.gui.drawable.SimpleDrawablePane;
import javafx.beans.InvalidationListener;
import javafx.scene.canvas.Canvas;

public class PlatformPane extends SimpleDrawablePane {

    private final Canvas canvas;

    private LogicCircuit circuit;
    private IDrawerFactory drawerFactory;

    public PlatformPane(IDrawerFactory drawerFactory) {
        this.circuit = new LogicCircuit(900, 600, 0, 0);
        this.drawerFactory = drawerFactory;

        this.canvas = new Canvas();
        canvas.widthProperty().bind(this.widthProperty());
        canvas.heightProperty().bind(this.heightProperty());

        InvalidationListener canvasResizeListener = (e) -> setDirty();
        canvas.widthProperty().addListener(canvasResizeListener);
        canvas.heightProperty().addListener(canvasResizeListener);

        getChildren().add(canvas);

        circuit.addElement(new LogicAndGate(3));

        drawingTimer.start();
    }

    public void draw() {
        IDrawer<LogicCircuit> drawer = (IDrawer<LogicCircuit>) drawerFactory.getDrawerFor(circuit.getClass());
        if (drawer != null)
            drawer.drawElement(drawerFactory, canvas.getGraphicsContext2D(), canvas, circuit);
        else throw new RuntimeException("circuit");
    }

    @Override
    public void resetDirty() {
        super.resetDirty();
    }
}
