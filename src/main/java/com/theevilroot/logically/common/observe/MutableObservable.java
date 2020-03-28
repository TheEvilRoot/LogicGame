package com.theevilroot.logically.common.observe;

public interface MutableObservable<T> extends Observable<T> {

    void setValue(T newValue);

}
