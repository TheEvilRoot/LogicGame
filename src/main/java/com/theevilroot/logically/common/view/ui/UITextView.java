package com.theevilroot.logically.common.view.ui;

import com.theevilroot.logically.core.Resources;
import javafx.scene.paint.Paint;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;

public class UITextView extends UIView{

    private String text;
    private String fontFamily;
    private float fontSize;
    private Paint textColor;

    public UITextView(double x, double y, String text, float fontSize, String fontFamily, Paint textColor) {
        super(x, y);
        this.text = text;
        this.fontSize = fontSize;
        this.fontFamily = fontFamily;
        this.textColor = textColor;
        recalculateSize();
    }

    public UITextView(double x, double y, String text, float fontSize, String fontFamily) {
        this(x, y, text, fontSize, fontFamily, Resources.ELEMENT_TEXT_COLOR);
    }

    public UITextView(double x, double y, String text, float fontSize) {
        this(x, y, text, fontSize, "System");
    }

    public UITextView(double x, double y, String text) {
        this(x, y, text, 12f);
    }

    public UITextView(String text) {
        this(0f, 0f, text);
    }

    public UITextView(double x, double y) {
        this(x, y, "");
    }

    public UITextView() {
        this(0f, 0f);
    }

    public void setText(String newText) {
        this.text = newText;
        recalculateSize();
    }

    public String getText() {
        return text;
    }

    public void setFontFamily(String ff) {
        this.fontFamily = ff;
        recalculateSize();
    }

    public String getFontFamily() {
        return fontFamily;
    }

    public void setTextColor(Paint textColor) {
        this.textColor = textColor;
    }

    public Paint getTextColor() {
        return textColor;
    }

    public void setFontSize(float fs) {
        this.fontSize = fs;
        recalculateSize();
    }

    public float getFontSize() {
        return fontSize;
    }

    private void recalculateSize() {
        Text dummy = new Text();
        dummy.setText(this.text);
        dummy.setFont(Font.font(fontFamily, fontSize));
        dummy.setTextAlignment(TextAlignment.LEFT);

        double width = dummy.getLayoutBounds().getWidth();
        double height = dummy.getLayoutBounds().getHeight();

        setSize(width, height);
    }

}
