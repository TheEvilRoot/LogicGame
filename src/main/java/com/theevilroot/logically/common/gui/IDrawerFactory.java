package com.theevilroot.logically.common.gui;

public interface IDrawerFactory {

    <V extends IView> IDrawer<? super V> getDrawerFor(Class<? super V> viewClass);

}
