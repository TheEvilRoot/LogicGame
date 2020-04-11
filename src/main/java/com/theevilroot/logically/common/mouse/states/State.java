package com.theevilroot.logically.common.mouse.states;

import com.theevilroot.logically.common.mouse.states.impl.StatefulSelectableView;

public enum State {

    SELECTED,
    HOVER,
    DIRTY,
    NONE;

    public static State fromSelectable(IStateful selectable) {
        if (selectable.is(StatefulSelectableView.HOVER))
            return HOVER;
        if (selectable.is(StatefulSelectableView.SELECTED))
            return SELECTED;
        if (selectable.is(StatefulSelectableView.DIRTY))
            return DIRTY;
        return NONE;
    }

    public static State fromState(int state) {
        if ((state & StatefulSelectableView.HOVER) > 0)
            return HOVER;
        if ((state & StatefulSelectableView.SELECTED) > 0)
            return SELECTED;
        if ((state & StatefulSelectableView.DIRTY) > 0)
            return DIRTY;
        return NONE;
    }

}
