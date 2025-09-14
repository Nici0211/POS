package circularprimes;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;

public class CircularPrimesWorker implements Callable<List<Integer>> {

    private final int lowerBound;
    private final int upperBound;

    public CircularPrimesWorker(int lowerBound, int upperBound) {
        this.lowerBound = lowerBound;
        this.upperBound = upperBound;
    }

    @Override
    public List<Integer> call() {
        List<Integer> circularPrimes = new ArrayList<>();
        for (int i = lowerBound; i < upperBound; i++) {
            if (isCircularPrime(i)) {
                circularPrimes.add(i);
            }
        }
        return circularPrimes;
    }

    private boolean isPrime(int n) {
        if (n < 2) return false;
        if (n == 2) return true;
        if (n % 2 == 0) return false;
        for (int i = 3; i <= Math.sqrt(n); i += 2) {
            if (n % i == 0)
            {
                return false;
            }
        }
        return true;
    }

    private boolean isCircularPrime(int n) {
        String numStr = Integer.toString(n);

        for (int i = 0; i < numStr.length(); i++) {
            int rotated = Integer.parseInt(numStr);
            if (!isPrime(rotated))
            {
                return false;
            }
            numStr = numStr.substring(1) + numStr.charAt(0);
        }

        return true;
    }
}
