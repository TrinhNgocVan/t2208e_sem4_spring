package org.example.utils.multithreading;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Lab2  implements Runnable{
    private int i;
    private volatile List<Integer> nums;

    public Lab2(int i , List<Integer> nums) {
        this.i = i;
        this.nums = nums;
    }

    public void run(){
//        try {
            System.err.println("Thread  "+i +"  is running");
//            Thread.sleep(1000);
            // add random number to list
            this.nums.add(new Random().nextInt());
//        } catch (InterruptedException e) {
//            throw new RuntimeException(e);
//        }
    }

    private static final int CPU_MAX_THREAD_INIT = 4;

    public static void main(String[] args) throws InterruptedException {


        List<Integer> nums  = new ArrayList<>();
        int  j  = 0 ;
        List<Thread> batchThread  = new ArrayList<>();
        for (int i = 1; i <= 1352525 ; i++) {
            Lab2 lab2 = new Lab2(i,nums);
            Thread t = new Thread(lab2);
            batchThread.add(t);
            if(batchThread.size() == CPU_MAX_THREAD_INIT ){
                for (Thread runThread : batchThread){
                    runThread.start();
                    runThread.join();
                }
                batchThread.clear();
            }
        }
        if(!batchThread.isEmpty()){
            for (Thread runThread : batchThread){
                runThread.start();
                runThread.join();
            }
            batchThread.clear();
        }

        System.err.println("List item  : " + nums);
        System.err.println("List size  : " + nums.size());
    }
}
