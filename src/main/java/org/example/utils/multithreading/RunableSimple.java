package org.example.utils.multithreading;

public class RunableSimple implements  Runnable{
    private int i;

    public RunableSimple(int i) {
        this.i = i;
    }

    public void run(){
        try {
            System.err.println("Thread  "+i +"  is running");
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    public static void main(String[] args) {
        long startTime  = System.currentTimeMillis();
        for (int i = 1; i <= 10; i++) {
            RunableSimple  runableThread  = new RunableSimple(i);
            Thread t = new Thread(runableThread);
            t.start(); // multi thread
        }
        System.err.println("Process time  = "+(System.currentTimeMillis() - startTime)+ " ms");


    }
}
