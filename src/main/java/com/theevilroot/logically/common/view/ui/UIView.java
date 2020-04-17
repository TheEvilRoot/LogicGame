package com.theevilroot.logically.common.view.ui;

import com.theevilroot.logically.core.math.Vector;
import com.theevilroot.logically.common.mouse.MouseHandler;
import com.theevilroot.logically.common.mouse.MouseTrace;
import com.theevilroot.logically.common.view.impl.BaseView;
import javafx.scene.input.MouseEvent;

public class UIView extends BaseView implements MouseHandler {

    public UIView(double x, double y) {
        super(x, y);
    }

    public UIView(double x, double y, double w, double h) {
        super(x, y, w, h);
    }

    public UIView(Vector pos) {
        super(pos);
    }

    public UIView(Vector pos, Vector size) {
        super(pos, size);
    }

    public UIView() {
        super();
    }

    @Override
    public boolean handle(MouseEvent event, Vector relPos, MouseTrace trace) {
        if (relPos.getX() >= 0 && relPos.getY() >= 0 && relPos.getX() < getSize().getX() && relPos.getY() < getSize().getY()) {
            trace.finish(this);
            return true;
        }
        return false;
    }
}
