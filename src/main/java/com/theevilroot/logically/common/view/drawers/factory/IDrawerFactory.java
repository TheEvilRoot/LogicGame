package com.theevilroot.logically.common.view.drawers.factory;

import com.theevilroot.logically.common.view.IView;
import com.theevilroot.logically.common.view.drawers.IDrawer;

public interface IDrawerFactory {

    <V extends IView> IDrawer<? super V> getDrawerFor(Class<? super V> viewClass);

}
