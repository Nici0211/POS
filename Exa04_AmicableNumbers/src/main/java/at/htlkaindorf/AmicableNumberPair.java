package at.htlkaindorf;

import java.util.Arrays;
import java.util.Objects;

public class AmicableNumberPair implements Comparable<AmicableNumberPair>{
    private int[] amicableNumberPair;

    public AmicableNumberPair(int number1, int number2) {
        amicableNumberPair = new int[2];
        amicableNumberPair[0] = number1;
        amicableNumberPair[1] = number2;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        AmicableNumberPair that = (AmicableNumberPair) o;
        return Objects.deepEquals(amicableNumberPair, that.amicableNumberPair);
    }

    @Override
    public int hashCode() {
        return Arrays.hashCode(amicableNumberPair);
    }

    @Override
    public int compareTo(AmicableNumberPair o) {
        return amicableNumberPair[0] - o.amicableNumberPair[0];
    }

    @Override
    public String toString() {
        return "(" + amicableNumberPair[0] + "; " + amicableNumberPair[1] + ")";
    }
}
