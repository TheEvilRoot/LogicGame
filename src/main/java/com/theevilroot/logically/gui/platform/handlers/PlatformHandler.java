package com.theevilroot.logically.gui.platform.handlers;

import com.theevilroot.logically.common.mouse.MouseHandler;
import com.theevilroot.logically.gui.drawable.impl.BaseDrawable;
import com.theevilroot.logically.gui.platform.IPlatform;

public abstract class PlatformHandler extends BaseDrawable implements MouseHandler {

    private final IPlatform platform;

    public PlatformHandler(IPlatform platform) {
        this.platform = platform;
    }

    protected IPlatform getPlatform() {
        return platform;
    }

    public void sizeUpdated(double w, double h) { }

}
