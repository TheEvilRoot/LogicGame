package com.theevilroot.logically.common.view.drawers.impl;

import com.theevilroot.logically.common.gui.TextView;
import com.theevilroot.logically.common.view.drawers.IDrawer;
import com.theevilroot.logically.common.view.drawers.factory.IDrawerFactory;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Paint;
import javafx.scene.text.Font;
import javafx.scene.text.TextAlignment;

public class TextViewDrawer implements IDrawer<TextView> {
    @Override
    public void drawElement(IDrawerFactory factory, GraphicsContext gc, Canvas cv, TextView view) {
        gc.setTextAlign(TextAlignment.RIGHT);
        gc.setFont(Font.font("FiraCode", 16f));
        gc.setFill(Paint.valueOf("#ff0000"));
        gc.fillText(view.getText(), view.getPosition().getX(), view.getPosition().getY());
    }
}
