package com.theevilroot.logically.gui;


import com.theevilroot.logically.common.elements.LogicCircuit;
import com.theevilroot.logically.common.elements.LogicElement;
import com.theevilroot.logically.common.elements.control.LogicInputPanel;
import com.theevilroot.logically.common.elements.control.LogicOutputPanel;
import com.theevilroot.logically.common.elements.base.LogicAndGate;
import com.theevilroot.logically.common.elements.base.LogicNotGate;
import com.theevilroot.logically.common.elements.base.LogicOrGate;
import com.theevilroot.logically.common.gui.TextView;
import com.theevilroot.logically.common.math.Vector;
import com.theevilroot.logically.common.mouse.MouseHandler;
import com.theevilroot.logically.common.mouse.MouseTrace;
import com.theevilroot.logically.common.ports.LogicPort;
import com.theevilroot.logically.common.view.IView;
import com.theevilroot.logically.common.view.drawers.IDrawer;
import com.theevilroot.logically.common.view.drawers.factory.IDrawerFactory;
import com.theevilroot.logically.gui.drawable.impl.SimpleDrawablePane;
import javafx.beans.InvalidationListener;
import javafx.event.EventHandler;
import javafx.scene.canvas.Canvas;
import javafx.scene.input.MouseEvent;

import java.util.ArrayList;

public class PlatformPane extends SimpleDrawablePane implements EventHandler<MouseEvent> {

    private final Canvas canvas;

    private LogicCircuit circuit;
    private IDrawerFactory drawerFactory;

    private ArrayList<IView> views = new ArrayList<>();

    private Vector mouse = new Vector(Vector.UNIT);

    public PlatformPane(IDrawerFactory drawerFactory) {
        this.circuit = new LogicCircuit(0, 0, 600, 900);
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

        canvas.setOnMouseMoved(e -> mouse.set(e.getX(), e.getY()));

        canvas.setOnMouseClicked(this);

        getChildren().add(canvas);

        LogicInputPanel input = new LogicInputPanel(-50f, 0, 4);
        LogicOutputPanel output = new LogicOutputPanel(500f, 0f, 1);

        LogicAndGate and = new LogicAndGate(20f, 20f, 3);
        LogicOrGate or = new LogicOrGate(350f, 20f, 2);
        LogicNotGate not = new LogicNotGate(200f, 20f);

        and.connectPort(0, not, 0);
        not.connectPort(0, or, 0);

        input.connectPort(0, and, 0);
        input.connectPort(1, and, 1);
        input.connectPort(2, and, 2);
        input.connectPort(3, or, 1);

        or.connectPort(0, output, 0);


        circuit.addElement(not);
        circuit.addElement(and);
        circuit.addElement(or);
        circuit.addElement(input);
        circuit.addElement(output);

        TextView textView = new TextView(200, 200, "TextView");
        views.add(textView);

        drawingTimer.start();
    }

    public void draw() {
        boolean shouldBeRedrawnLater = circuit.handleHover(mouse);
        IDrawer<LogicCircuit> drawer = (IDrawer<LogicCircuit>) drawerFactory.getDrawerFor(circuit.getClass());
        if (drawer != null)
            drawer.drawElement(drawerFactory, canvas.getGraphicsContext2D(), canvas, circuit);
        else throw new RuntimeException("circuit");

        views.forEach(e -> {
            IDrawer<IView> viewDrawer = (IDrawer<IView>) drawerFactory.getDrawerFor(e.getClass());
            if (drawer != null) {
               viewDrawer.drawElement(drawerFactory, canvas.getGraphicsContext2D(), canvas, e);
            }
        });

        if (shouldBeRedrawnLater)
            setDirty();
    }

    @Override
    public void resetDirty() {
        super.resetDirty();
    }

    @Override
    public void handle(MouseEvent mouseEvent) {
        Vector absMouse = new Vector(mouseEvent.getX(), mouseEvent.getY());
        Vector relMouse = Vector.minus(absMouse, circuit.getPosition());
        MouseTrace trace = new MouseTrace();
        circuit.handle(mouseEvent, relMouse, trace);
        if (trace.getAcceptor() != null) {
            MouseHandler acceptor = trace.getAcceptor();
            if (acceptor instanceof LogicCircuit) {
                circuit.onCircuitClick(mouseEvent, relMouse);
            } else if (acceptor instanceof LogicElement) {
                circuit.onElementClick(mouseEvent, relMouse, (LogicElement) acceptor);
            } else if (acceptor instanceof LogicPort) {
                MouseHandler lastTrace = trace.getTrace().get(trace.getTrace().size() - 1);
                if (lastTrace instanceof LogicElement)
                    circuit.onElementPortClick(mouseEvent, relMouse, (LogicElement) lastTrace, (LogicPort) acceptor);
            }
        }

        setDirty();
    }
}
