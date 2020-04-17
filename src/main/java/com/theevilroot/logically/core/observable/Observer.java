package com.theevilroot.logically.core.observable;

public interface Observer<T> {

    void valueUpdated(T newValue);

}
