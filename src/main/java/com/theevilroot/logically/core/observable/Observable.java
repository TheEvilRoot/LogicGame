package com.theevilroot.logically.core.observable;

public interface Observable<T> {

    T getValue();

    Boolean hasValue();

    Observable<T> subscribe(Observer<T> observer);

    Observable<T> unsubscribe(Observer<T> observer);

    void notifyChanged(T newValue);

}
