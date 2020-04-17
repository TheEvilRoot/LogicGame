package com.theevilroot.logically.gui.platform.handlers;

import com.theevilroot.logically.common.elements.LogicCircuit;
import com.theevilroot.logically.common.elements.LogicElement;
import com.theevilroot.logically.core.math.Vector;
import com.theevilroot.logically.common.mouse.MouseHandler;
import com.theevilroot.logically.common.mouse.MouseTrace;
import com.theevilroot.logically.common.mouse.states.IStateful;
import com.theevilroot.logically.common.ports.LogicPort;
import com.theevilroot.logically.common.view.drawers.IDrawer;
import com.theevilroot.logically.gui.platform.IPlatform;
import javafx.scene.input.MouseEvent;

public class PlatformCircuitHandler extends PlatformHandler {

    private final LogicCircuit circuit;

    public PlatformCircuitHandler(IPlatform platform) {
        super(platform);
        this.circuit = new LogicCircuit(0, 0, 600, 900);
    }

    public void addElement(LogicElement element) {
        circuit.addElement(element);
        setDirty();
    }

    @Override
    public boolean shouldBeRedrawn() {
        return super.shouldBeRedrawn() || circuit.is(IStateful.DIRTY);
    }

    @Override
    public void sizeUpdated(double w, double h) {
        circuit.setSize(w, h);
        setDirty();
    }

    @Override
    public boolean handle(MouseEvent event, Vector absMouse, MouseTrace trace) {
        Vector relMouse = Vector.minus(absMouse, circuit.getPosition());
        circuit.handle(event, relMouse, trace);
        if (trace.getAcceptor() != null) {
            MouseHandler acceptor = trace.getAcceptor();
            if (acceptor instanceof LogicCircuit) {
                circuit.onCircuitClick(event, relMouse);
            } else if (acceptor instanceof LogicElement) {
                circuit.onElementClick(event, relMouse, (LogicElement) acceptor);
            } else if (acceptor instanceof LogicPort) {
                MouseHandler lastTrace = trace.getTrace().get(trace.getTrace().size() - 1);
                if (lastTrace instanceof LogicElement)
                    circuit.onElementPortClick(event, relMouse, (LogicElement) lastTrace, (LogicPort) acceptor);
            }
        }
        return false;
    }

    @Override
    public void draw() {
        circuit.handleHover(getPlatform().getMouse());
        IDrawer<? super LogicCircuit> drawer = getPlatform().getDrawerFactory().getDrawerFor(circuit.getClass());
        if (drawer != null)
            drawer.drawElement(getPlatform().getDrawerFactory(), getPlatform().getCanvas().getGraphicsContext2D(), getPlatform().getCanvas(), circuit);
        else throw new RuntimeException("circuit");
    }

}
