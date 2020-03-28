package com.theevilroot.logically.common.gui;

public interface IDrawerFactory {

    IDrawer<IView> getDrawerFor(Class<? extends IView> viewClass);
}
