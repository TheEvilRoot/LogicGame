package com.theevilroot.logically.common.observe;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;

public class SimpleObservable<T> implements Observable<T> {

    protected Set<Observer<T>> observers;

    public SimpleObservable() {
        this.observers = new HashSet<>();
    }

    @Override
    public Observable<T> subscribe(Observer<T> observer) {
        observers.add(observer);
        return this;
    }

    @Override
    public Observable<T> unsubscribe(Observer<T> observer) {
        observers.remove(observer);
        return this;
    }

    @Override
    public void notify(T oldValue, T newValue) {
        observers.forEach(observer -> observer.valueUpdated(oldValue, newValue));
    }
}
