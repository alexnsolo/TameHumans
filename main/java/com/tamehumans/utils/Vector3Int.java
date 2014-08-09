package com.tamehumans.utils;

public class Vector3Int {

    public int x;
    public int y;
    public int z;

    public Vector3Int(int x, int y, int z) {
        this.x = x;
        this.y = y;
        this.z = z;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Vector3Int vectorInt = (Vector3Int) o;

        if (x != vectorInt.x) return false;
        if (y != vectorInt.y) return false;
        if (z != vectorInt.z) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = x;
        result = 31 * result + y;
        result = 31 * result + z;
        return result;
    }
}
