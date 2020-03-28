package com.theevilroot.logically.common.gui;

import com.theevilroot.logically.common.gui.IDrawer;
import com.theevilroot.logically.common.gui.IView;

import java.util.HashMap;
import java.util.Map;

public class DrawerFactory implements IDrawerFactory {

    private Map<Class<? extends IView>, IDrawer<? extends IView>> drawers = new HashMap<>();

    public <VT extends IView> void addDrawer(Class<VT> viewClass, IDrawer<? extends IView> drawer) {
        drawers.put(viewClass, drawer);
    }

    public IDrawer<IView> getDrawerFor(Class<? extends IView> viewClass) {
        if (viewClass == IView.class)
            return null;
        if (drawers.containsKey(viewClass))
            return (IDrawer<IView>) drawers.get(viewClass);
        if (drawers.containsKey(viewClass.getSuperclass()))
            return (IDrawer<IView>) drawers.get(viewClass.getSuperclass());
        return null;
    }
}
