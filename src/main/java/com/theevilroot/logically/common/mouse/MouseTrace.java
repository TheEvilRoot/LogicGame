package com.theevilroot.logically.common.mouse;

import java.util.ArrayList;
import java.util.List;

public class MouseTrace {

    private List<MouseHandler> trace = new ArrayList<>();
    private MouseHandler acceptor = null;

    public MouseTrace() { }

    public void addTrace(MouseHandler handler) {
        trace.add(handler);
    }

    public void finish(MouseHandler finishHandler) {
        acceptor = finishHandler;
    }

    public MouseHandler getAcceptor() {
        return acceptor;
    }

    public List<MouseHandler> getTrace() {
        return trace;
    }

    public void reset() {
        this.trace.clear();
        this.acceptor = null;
    }

}
