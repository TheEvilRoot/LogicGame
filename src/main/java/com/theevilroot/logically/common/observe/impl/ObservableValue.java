package com.theevilroot.logically.common.observe.impl;

import com.theevilroot.logically.common.observe.MutableObservable;
import com.theevilroot.logically.common.observe.Observable;
import com.theevilroot.logically.common.observe.Observer;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;

public class ObservableValue<T> implements MutableObservable<T> {

    protected Set<Observer<T>> observers;

    private T value;

    public ObservableValue(T initialValue) {
        this.observers = new HashSet<>();
        this.value = initialValue;
    }

    public ObservableValue() {
        this(null);
    }

    @Override
    public T getValue() {
        return value;
    }

    @Override
    public Boolean hasValue() {
        return value != null;
    }

    @Override
    public Observable<T> subscribe(Observer<T> observer) {
        observers.add(observer);
        observer.valueUpdated(value);
        return this;
    }

    @Override
    public Observable<T> unsubscribe(Observer<T> observer) {
        observers.remove(observer);
        return this;
    }

    @Override
    public void notifyChanged(T newValue) {
        observers.forEach(observer -> observer.valueUpdated(newValue));
    }

    @Override
    public void setValue(T newValue) {
        this.value = newValue;
        notifyChanged(newValue);
    }

    @Override
    public String toString() {
        return value.toString();
    }
}
