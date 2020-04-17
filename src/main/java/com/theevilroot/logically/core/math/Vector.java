package com.theevilroot.logically.core.math;

public class Vector {

    public static final Vector ZERO = new Vector(0.0, 0.0);
    public static final Vector UNIT = new Vector(1.0, 1.0);

    private double x;
    private double y;

    public Vector(double x, double y) {
        this.x = x;
        this.y = y;
    }

    public Vector(Vector source) {
        this(source.x, source.y);
    }

    public double getX() {
        return x;
    }

    public double getY() {
        return y;
    }

    public Vector add(Vector a) {
        this.x += a.x;
        this.y += a.y;
        return this;
    }

    public Double xySum() {
        return this.x + this.y;
    }

    public static Vector plus(Vector a, Vector b) {
        return new Vector(a.x + b.x, a.y + b.y);
    }

    public Vector subtract(Vector a) {
        this.x -= a.x;
        this.y -= a.y;
        return this;
    }

    public static Vector minus(Vector a, Vector b) {
        return new Vector(a.x - b.x, a.y - b.y);
    }

    public Vector extend(double constant) {
        this.x *= constant;
        this.y *= constant;
        return this;
    }

    public static Vector multiply(Vector a, double constant) {
        Vector v = new Vector(a);
        v.extend(constant);
        return v;
    }

    public Vector set(double x, double y) {
        this.x = x;
        this.y = y;
        return this;
    }

    public Vector abs() {
        this.x = Math.abs(x);
        this.y = Math.abs(y);
        return this;
    }

    public double getSquaredMag() {
        return (x * x + y * y);
    }

    public double getMag() {
        return Math.sqrt(getSquaredMag());
    }


    @Override
    public String toString() {
        return "Vector{" +
                "x=" + x +
                ", y=" + y +
                '}';
    }
}
