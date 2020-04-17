package com.theevilroot.logically.common.view.ui;

import com.theevilroot.logically.core.math.Vector;
import com.theevilroot.logically.common.mouse.MouseTrace;
import com.theevilroot.logically.common.view.ui.listeners.UIClickListener;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Paint;

public class UIButton extends UITextView {

    private UIClickListener listener;

    public UIButton(double x, double y, String text, float fontSize, String fontFamily, Paint textColor) {
        super(x, y, text, fontSize, fontFamily, textColor);
    }

    public UIButton(double x, double y, String text, float fontSize, String fontFamily) {
        super(x, y, text, fontSize, fontFamily);
    }

    public UIButton(double x, double y, String text, float fontSize) {
        super(x, y, text, fontSize);
    }

    public UIButton(double x, double y, String text) {
        super(x, y, text);
    }

    public UIButton(String text) {
        super(text);
    }

    public UIButton(double x, double y) {
        super(x, y);
    }

    public UIButton() {
        super();
    }

    public void invokeClick(MouseEvent event) {
        if (listener != null)
            listener.onClick(event, this);
    }

    public void setClickListener(UIClickListener listener) {
        this.listener = listener;
    }

    @Override
    public boolean handle(MouseEvent event, Vector relPos, MouseTrace trace) {
        if (super.handle(event, relPos, trace)) {
            invokeClick(event);
            return true;
        }
        return false;
    }
}
