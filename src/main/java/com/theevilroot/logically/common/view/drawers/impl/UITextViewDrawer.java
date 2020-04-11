package com.theevilroot.logically.common.view.drawers.impl;

import com.theevilroot.logically.common.view.drawers.IDrawer;
import com.theevilroot.logically.common.view.drawers.factory.IDrawerFactory;
import com.theevilroot.logically.common.view.ui.UITextView;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Paint;
import javafx.scene.text.Font;
import javafx.scene.text.TextAlignment;

public class UITextViewDrawer implements IDrawer<UITextView> {
    @Override
    public void drawElement(IDrawerFactory factory, GraphicsContext gc, Canvas cv, UITextView view) {
        gc.setStroke(Paint.valueOf("#ff00ff"));
        gc.setLineWidth(1f);
        gc.strokeRect(view.getPosition().getX(), view.getPosition().getY(), view.getSize().getX(), view.getSize().getY());

        gc.setFill(view.getTextColor());
        gc.setFont(Font.font(view.getFontFamily(), view.getFontSize()));
        gc.setTextAlign(TextAlignment.LEFT);

        gc.fillText(view.getText(), view.getPosition().getX(), view.getPosition().getY() + view.getSize().getY());
    }
}
