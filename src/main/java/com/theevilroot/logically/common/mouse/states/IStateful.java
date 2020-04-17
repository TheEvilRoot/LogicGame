package com.theevilroot.logically.common.mouse.states;

public interface IStateful {

    boolean is(int mask);

    void set(int mask);

    void unset(int mask);

    boolean can(int mm);

    void allow(int mm);

    void deny(int mm);

    int SELECTED = 1;
    int HOVER = 2;
    int DIRTY = 4;
    int INPUT_CHANGEABLE = 8;

}
