package com.geek.homework.week04.v2;

/**
 * 可以使用 sleep() 使主线程睡眠，以等待子线程执行完毕
 *  因不能确定子线程执行耗时，实际中并不使用该方法
 */
public class Thread02 extends Thread {

    private static int RESULT = 0;

    public static void main(String[] args) throws InterruptedException {

        System.out.println("当前线程====" + Thread.currentThread().getName());

        long start = System.currentTimeMillis();

        //创建一个线程，并进行阻塞
        Thread myThread = new Thread02();
        myThread.start();

        // 睡眠200ms
        Thread.sleep(1000);

        System.out.println("异步计算结果为：" + RESULT);

        System.out.println("使用时间：" + (System.currentTimeMillis() - start) + " ms");
    }

    private static int sum() {
        System.out.println("当前线程====" + Thread.currentThread().getName());
        return fibo(36);
    }

    private static int fibo(int a) {
        if (a < 2)
            return 1;
        return fibo(a - 1) + fibo(a - 2);
    }

    @Override
    public void run() {
        RESULT = sum();
    }
}
