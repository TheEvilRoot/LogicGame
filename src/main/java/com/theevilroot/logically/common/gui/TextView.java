package com.theevilroot.logically.common.gui;

import com.theevilroot.logically.common.view.impl.BaseView;


public class TextView extends BaseView {

    private String text;

    public TextView(double x, double y, String text) {
        super(x, y);
        this.text = text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getText() {
        return text;
    }

}
