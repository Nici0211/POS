package at.htlkaindorf;


import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;

public class NumbersWorker implements Callable<List<Integer>> {
    private final int start;
    private final int end;

    public NumbersWorker(int start, int end) {
        this.start = start;
        this.end = end;
    }

    @Override
    public List<Integer> call() {
        List<Integer> narcissisticNumbers = new ArrayList<>();
        for (int i = start; i < end; i++) {
            if (isNarcissistic(i)) {
                narcissisticNumbers.add(i);
            }
        }
        return narcissisticNumbers;
    }

    private boolean isNarcissistic(int number) {
        String numStr = Integer.toString(number);
        int sum = 0;
        for (int i = 0; i < numStr.length(); i++) {
            int digit = numStr.charAt(i) - '0';
            sum += Math.pow(digit, i + 1);
        }
        return sum == number;
    }
}
