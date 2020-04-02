package com.theevilroot.logically.common.mouse.selection;

public interface ISelectable {

    boolean is(int mask);

    void set(int mask);

    void unset(int mask);

    State getState();

}
