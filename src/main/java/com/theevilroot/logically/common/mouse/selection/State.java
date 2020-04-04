package com.theevilroot.logically.common.mouse.selection;

import com.theevilroot.logically.common.mouse.selection.impl.Stateful;

public enum State {

    SELECTED,
    HOVER,
    NONE;

    public static State fromSelectable(IStateful selectable) {
        if (selectable.is(Stateful.HOVER))
            return HOVER;
        if (selectable.is(Stateful.SELECTED))
            return SELECTED;
        return NONE;
    }

    public static State fromState(int state) {
        if ((state & Stateful.HOVER) > 0)
            return HOVER;
        if ((state & Stateful.SELECTED) > 0) {
            return SELECTED;
        }
        return NONE;
    }

}
