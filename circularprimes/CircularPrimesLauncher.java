package circularprimes;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.concurrent.*;

public class CircularPrimesLauncher {
    public static void main(String[] args) throws InterruptedException, ExecutionException {
        Scanner sc = new Scanner(System.in);

        int valuesPerThread;
        int threadCount;

            System.out.print("number of values per thread: ");
            valuesPerThread = sc.nextInt();
            System.out.print("number of threads per pool: ");
            threadCount = sc.nextInt();


        ExecutorService pool = Executors.newFixedThreadPool(threadCount);
        List<Callable<List<Integer>>> numbers = new ArrayList<>();

        int totalValues = valuesPerThread * threadCount;
        int step = totalValues / threadCount;
        int start = 2;

        for (int i = 0; i < threadCount; i++) {
            int end = Math.min(start + step, totalValues);
            numbers.add(new CircularPrimesWorker(start,end));
            //futures.add(pool.submit(new CircularPrimesWorker(start, end)));
            start = end;
        }
        List<Future<List<Integer>>> futures = pool.invokeAll(numbers);

        pool.shutdown();

        int totalFound = 0;
        start = 2;
        for (Future<List<Integer>> future : futures) {
            int end = Math.min(start + step, totalValues);
            List<Integer> result = future.get();

            if (result.isEmpty())
            {
                System.out.print("numbers in range [" + start + ";" + end + "]: no numbers found");;
            }else {
                System.out.print("numbers in range [" + start + ";" + end + "]: ");
            }
            for (int i = 0; i<result.size();i++)
            {
                System.out.print(result.get(i));
                if (i<result.size()-1)
                {
                    System.out.print(" ,");
                }
            }
            System.out.println();
            totalFound += result.size();
            start = end;
        }

        System.out.println("number of circular primes in the range [2;" + totalValues + "]: " + totalFound);


    }
}
