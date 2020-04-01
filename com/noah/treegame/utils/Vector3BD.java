package com.noah.treegame.utils;

public class Vector3BD {

    public BigDouble x, y, z;

    public Vector3BD(BigDouble x, BigDouble y, BigDouble z) {
        this.x = x;
        this.y = y;
        this.z = z;
    }

    public String toString() {
        return this.x + ", " + this.y + ", " + this.z;
    }
}
