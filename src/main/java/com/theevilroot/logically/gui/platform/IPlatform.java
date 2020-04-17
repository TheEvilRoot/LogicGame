package com.theevilroot.logically.gui.platform;

import com.theevilroot.logically.common.elements.LogicElement;
import com.theevilroot.logically.core.math.Vector;
import com.theevilroot.logically.common.view.drawers.factory.IDrawerFactory;
import com.theevilroot.logically.common.view.ui.UIView;
import javafx.scene.canvas.Canvas;

public interface IPlatform {

    Canvas getCanvas();

    Vector getMouse();

    IDrawerFactory getDrawerFactory();

    void addElement(LogicElement element);

    void addView(UIView view);

}
