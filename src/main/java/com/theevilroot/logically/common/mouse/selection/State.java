package com.theevilroot.logically.common.mouse.selection;

import com.theevilroot.logically.common.mouse.selection.impl.Selectable;

public enum State {

    SELECTED,
    HOVER,
    NONE;

    public static State fromSelectable(ISelectable selectable) {
        if (selectable.is(Selectable.HOVER))
            return HOVER;
        if (selectable.is(Selectable.SELECTED))
            return SELECTED;
        return NONE;
    }

}
