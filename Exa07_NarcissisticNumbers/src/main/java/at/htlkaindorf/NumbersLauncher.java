package at.htlkaindorf;


import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.concurrent.*;

public class NumbersLauncher {


    public static void main(String[] args) throws InterruptedException, ExecutionException {
        Scanner scanner = new Scanner(System.in);

        System.out.print("upper bound of interval: ");
        int upperBound = scanner.nextInt();

        System.out.print("number of numbers per thread: ");
        int numbersPerThread = scanner.nextInt();

        System.out.print("number of worker threads: ");
        int threadCount = scanner.nextInt();

        System.out.println("Lets goooo");

        ExecutorService executor = Executors.newFixedThreadPool(threadCount);
        List<Callable<List<Integer>>> numbers = new ArrayList<>();

        int lowerBound = 1;
        for (int i = 0; lowerBound <= upperBound; i++) {
            int end = Math.min(lowerBound+numbersPerThread-1, upperBound);
            numbers.add(new NumbersWorker(lowerBound,end));

            lowerBound = end + 1;
        }
        List<Future<List<Integer>>>futures = executor.invokeAll(numbers);

        executor.shutdown();

        lowerBound = 1;

        for (Future<List<Integer>> future : futures) {
            int end = Math.min(lowerBound+numbersPerThread-1, upperBound);
            List<Integer> found = future.get();
            System.out.printf("number [ %5d; %5d ]: ",lowerBound, end);
            for (int i = 0; i<found.size();i++)
            {
                System.out.print(found.get(i));
                if (i<found.size()-1)
                {
                    System.out.print(", ");
                }
            }
            System.out.println();
            lowerBound = end+1;
        }
    }
}
