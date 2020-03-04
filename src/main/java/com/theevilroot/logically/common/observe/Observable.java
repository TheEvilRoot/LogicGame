package com.theevilroot.logically.common.observe;

public interface Observable<T> {

    Observable<T> subscribe(Observer<T> observer);

    Observable<T> unsubscribe(Observer<T> observer);

    void notify(T oldValue, T newValue);

}
