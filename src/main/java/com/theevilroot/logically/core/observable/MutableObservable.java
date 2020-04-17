package com.theevilroot.logically.core.observable;

public interface MutableObservable<T> extends Observable<T> {

    void setValue(T newValue);

}
