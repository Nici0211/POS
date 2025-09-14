package at.htlkaindorf;

import java.util.*;
import java.util.concurrent.*;

public class AmicableNumberLauncher {
    public static void main(String[] args) {
        int lowerBound;
        int upperBound;
        Scanner scanner = new Scanner(System.in);
        final int MAX_WORKERS = 5;

        do {
            System.out.printf("unterer Bereich: ");
            lowerBound = scanner.nextInt();
            System.out.printf("oberer Bereich: ");
            upperBound = scanner.nextInt();
        } while ((lowerBound < 100 || lowerBound > 3000) ||
                (upperBound < 100 || upperBound > 3000) ||
                (lowerBound >= upperBound));

        int increment = (upperBound - lowerBound) / MAX_WORKERS;

        ExecutorService pool = Executors.newFixedThreadPool(5);
        List<Callable<Set<AmicableNumberPair>>> amicableNumberPairs = new ArrayList<>();

        for (int i = 0; i < MAX_WORKERS; i++) {
            amicableNumberPairs.add(new AmicableNumberWorker(upperBound, lowerBound, increment, i));
        }
        Set<AmicableNumberPair> trueSet = new TreeSet<>();
        try {

            List<Future<Set<AmicableNumberPair>>> futures = pool.invokeAll(amicableNumberPairs);

            for (Future<Set<AmicableNumberPair>> future : futures) {
                Set<AmicableNumberPair> tempSet = future.get();

                for (AmicableNumberPair pairToAdd : tempSet) {
                    trueSet.add(pairToAdd);
                }
            }

        } catch (InterruptedException | ExecutionException e) {
            throw new RuntimeException(e);
        }

        pool.shutdown();

        for (AmicableNumberPair pair : trueSet) {
            System.out.println(pair);
        }

        System.out.println("Anzahl der Zahlenpaare: " + trueSet.size());
        System.out.println("BYEEE");
    }
}
