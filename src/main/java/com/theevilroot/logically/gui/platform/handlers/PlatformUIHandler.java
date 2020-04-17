package com.theevilroot.logically.gui.platform.handlers;

import com.theevilroot.logically.core.math.Vector;
import com.theevilroot.logically.common.mouse.MouseTrace;
import com.theevilroot.logically.common.view.IView;
import com.theevilroot.logically.common.view.drawers.IDrawer;
import com.theevilroot.logically.common.view.ui.UIView;
import com.theevilroot.logically.gui.platform.IPlatform;
import javafx.scene.input.MouseEvent;

import java.util.ArrayList;

public class PlatformUIHandler extends PlatformHandler {

    private final ArrayList<UIView> uiViews = new ArrayList<>();

    public PlatformUIHandler(IPlatform platform) {
        super(platform);
    }

    public void addView(UIView view) {
        uiViews.add(view);
        setDirty();
    }

    @Override
    public boolean handle(MouseEvent event, Vector absMouse, MouseTrace trace) {
        for (UIView uiView : uiViews) {
            Vector relMouse = Vector.minus(absMouse, uiView.getPosition());
            if (uiView.handle(event, relMouse, trace)) {
                setDirty();
                return true;
            }
            trace.reset();
        }
        return false;
    }

    @Override
    public void draw() {
        uiViews.forEach(ui -> {
            IDrawer<? super IView> uiDrawer = getPlatform().getDrawerFactory().getDrawerFor(ui.getClass());
            if (uiDrawer != null) {
                uiDrawer.drawElement(getPlatform().getDrawerFactory(), getPlatform().getCanvas().getGraphicsContext2D(), getPlatform().getCanvas(), ui);
            } else throw new RuntimeException(ui.getClass().getSimpleName());
        });
    }
}
