package com.theevilroot.logically.common.mouse.selection;

import com.theevilroot.logically.common.mouse.selection.impl.StatefulSelectableView;

public enum State {

    SELECTED,
    HOVER,
    NONE;

    public static State fromSelectable(IStateful selectable) {
        if (selectable.is(StatefulSelectableView.HOVER))
            return HOVER;
        if (selectable.is(StatefulSelectableView.SELECTED))
            return SELECTED;
        return NONE;
    }

    public static State fromState(int state) {
        if ((state & StatefulSelectableView.HOVER) > 0)
            return HOVER;
        if ((state & StatefulSelectableView.SELECTED) > 0) {
            return SELECTED;
        }
        return NONE;
    }

}
