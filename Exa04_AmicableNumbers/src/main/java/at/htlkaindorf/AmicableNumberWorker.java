package at.htlkaindorf;

import java.util.Set;
import java.util.TreeSet;
import java.util.concurrent.Callable;

public class AmicableNumberWorker implements Callable<Set<AmicableNumberPair>> {
    private int maxUpperBound;
    private int minLowerBound;
    private int increment;
    private int index;
    private Set<AmicableNumberPair> numberPairSet;

    public AmicableNumberWorker(int maxUpperBound, int minLowerBound, int increment, int index) {
        this.maxUpperBound = maxUpperBound;
        this.minLowerBound = minLowerBound;
        this.increment = increment;
        this.index = index;
    }

    @Override
    public Set<AmicableNumberPair> call() throws Exception {
        //System.out.println(index + " started!");
        numberPairSet = new TreeSet<>();

        int lowerBound = index == 0 ? minLowerBound : increment * index + minLowerBound + 1;
        int upperBound = lowerBound + increment - 1;

        for (int i = lowerBound; i <= upperBound; i++) {
            int dividerSum = 1;
            int dividerSumCheck = 1;

            for (int j = 2; j <= (i/2); j++) {
                if (i % j == 0)
                    dividerSum += j;
            }

            for (int j = 2; j <= (dividerSum/2); j++) {
                if (dividerSum % j == 0)
                    dividerSumCheck += j;
            }

            if (dividerSumCheck == i && i < dividerSum && dividerSum <= maxUpperBound){
                AmicableNumberPair pair = new AmicableNumberPair(i, dividerSum);
                System.out.println("found : " + pair);
                numberPairSet.add(pair);
            }
        }

        //System.out.println(index + " finished!");

        return numberPairSet;
    }
}
