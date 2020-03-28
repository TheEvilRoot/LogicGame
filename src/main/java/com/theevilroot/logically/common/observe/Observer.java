package com.theevilroot.logically.common.observe;

public interface Observer<T> {

    void valueUpdated(T newValue);

}
