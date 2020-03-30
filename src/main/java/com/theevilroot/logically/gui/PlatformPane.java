package com.theevilroot.logically.gui;


import com.theevilroot.logically.common.elements.LogicCircuit;
import com.theevilroot.logically.common.elements.base.LogicAndGate;
import com.theevilroot.logically.common.elements.base.LogicNotGate;
import com.theevilroot.logically.common.elements.base.LogicOrGate;
import com.theevilroot.logically.common.math.Vector;
import com.theevilroot.logically.common.view.drawers.IDrawer;
import com.theevilroot.logically.common.view.drawers.factory.IDrawerFactory;
import com.theevilroot.logically.gui.drawable.impl.SimpleDrawablePane;
import javafx.beans.InvalidationListener;
import javafx.event.EventHandler;
import javafx.scene.canvas.Canvas;
import javafx.scene.input.MouseEvent;

public class PlatformPane extends SimpleDrawablePane implements EventHandler<MouseEvent> {

    private final Canvas canvas;

    private LogicCircuit circuit;
    private IDrawerFactory drawerFactory;

    public PlatformPane(IDrawerFactory drawerFactory) {
        this.circuit = new LogicCircuit(0, 0, 900, 600);
        this.drawerFactory = drawerFactory;

        this.canvas = new Canvas();
        canvas.widthProperty().bind(this.widthProperty());
        canvas.heightProperty().bind(this.heightProperty());

        InvalidationListener canvasResizeListener = (e) -> {
            circuit.setSize(canvas.getWidth(), canvas.getHeight());
            setDirty();
        };
        canvas.widthProperty().addListener(canvasResizeListener);
        canvas.heightProperty().addListener(canvasResizeListener);

        canvas.setOnMouseClicked(this);

        getChildren().add(canvas);

        LogicAndGate and = new LogicAndGate(20f, 20f, 3);
        LogicOrGate or = new LogicOrGate(350f, 20f, 2);
        LogicNotGate not = new LogicNotGate(200f, 20f);

        and.connectPort(0, not, 0);
        not.connectPort(0, or, 0);

        circuit.addElement(not);
        circuit.addElement(and);
        circuit.addElement(or);

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

    @Override
    public void handle(MouseEvent mouseEvent) {
        Vector absMouse = new Vector(mouseEvent.getX(), mouseEvent.getY());
        Vector relMouse = Vector.minus(absMouse, circuit.getPosition());
        circuit.handle(mouseEvent, relMouse);
    }
}
