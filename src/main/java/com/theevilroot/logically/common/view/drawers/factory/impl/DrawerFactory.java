package com.theevilroot.logically.common.view.drawers.factory.impl;

import com.theevilroot.logically.common.view.IView;
import com.theevilroot.logically.common.view.drawers.IDrawer;
import com.theevilroot.logically.common.view.drawers.factory.IDrawerFactory;

import java.util.HashMap;
import java.util.Map;

public class DrawerFactory implements IDrawerFactory {

    private Map<Class<? extends IView>, IDrawer<? extends IView>> drawerMap = new HashMap<>();

    public void addDrawer(Class<? extends IView> viewClass, IDrawer<? extends IView> drawer) {
        drawerMap.put(viewClass, drawer);
    }

    @Override
    public <V extends IView> IDrawer<? super V> getDrawerFor(Class<? super V> viewClass) {
        if (viewClass == IView.class)
            return null;
        if (drawerMap.containsKey(viewClass)) {
            return (IDrawer<? super V>) drawerMap.get(viewClass);
        } else {
            return getDrawerFor(viewClass.getSuperclass());
        }
    }
}
