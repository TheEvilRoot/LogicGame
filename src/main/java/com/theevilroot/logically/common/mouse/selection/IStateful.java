package com.theevilroot.logically.common.mouse.selection;

public interface IStateful {

    boolean canHaveState(State state);

    boolean is(int mask);

    void set(int mask);

    void unset(int mask);

    State getState();

}
