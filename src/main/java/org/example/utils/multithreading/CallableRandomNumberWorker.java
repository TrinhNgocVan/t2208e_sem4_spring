package org.example.utils.multithreading;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.*;

public class CallableRandomNumberWorker implements Callable<Integer> {

    @Override
    public Integer call() throws Exception {
        return new Random().nextInt();
    }
    private static final int CPU_MAX_THREAD_INIT = 4;

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        List<Future<Integer>> futures = new ArrayList<>();
        ExecutorService executorService = Executors.newFixedThreadPool(CPU_MAX_THREAD_INIT);
        CallableRandomNumberWorker callable;
        Future<Integer> f;
        for (int i = 0; i < 1000 ; i++) {
            callable = new CallableRandomNumberWorker();
            f = executorService.submit(callable);
            futures.add(f);
        }

        executorService.shutdown();
        while (!executorService.isTerminated()){
            System.err.println("Executor is running ");
        }
        System.err.println("Executor is terminated");
        List<Integer> nums = new ArrayList<>();
        for (Future<Integer>  fus : futures){
            nums.add(fus.get());
        }
        System.err.println(nums);
//        System.err.println(futures.stream().map(Future::get).toList());
    }
}
