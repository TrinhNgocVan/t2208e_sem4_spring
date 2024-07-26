package org.example.utils.multithreading;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Lab3 implements Runnable {
    private int i;
    private volatile List<Integer> nums;

    public Lab3(int i , List<Integer> nums) {
        this.i = i;
        this.nums = nums;
    }

    public void run(){
        System.err.println("Thread  "+i +"  is running");
        this.nums.add(new Random().nextInt());
    }

    private static final int CPU_MAX_THREAD_INIT = 4;

    public static void main(String[] args) {
        ExecutorService executorService = Executors.newFixedThreadPool(CPU_MAX_THREAD_INIT);
        List<Integer> nums  = new ArrayList<>();
        for (int i = 0; i < 1000; i++) {
            Lab3 lab3 = new Lab3(i, nums);
            executorService.execute(lab3);
        }

        executorService.shutdown();
        while (!executorService.isTerminated()){
            System.err.println("Executor is running ");
        }
        System.err.println("Executor is terminated");
        System.err.println("Nums : "+ nums);
    }
}
