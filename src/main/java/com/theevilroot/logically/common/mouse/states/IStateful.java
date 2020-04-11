package com.theevilroot.logically.common.mouse.states;

public interface IStateful {

    boolean canHaveState(State state);

    boolean is(int mask);

    void set(int mask);

    void unset(int mask);

    State getState();

    int SELECTED = 1;
    int HOVER = 2;
    int DIRTY = 3;

}
