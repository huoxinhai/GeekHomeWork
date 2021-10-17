package com.geek.homework.week04.v2;

/**
 * Thread join() 用法
 *
 * 主线程的代码块中，如果碰到了t.join()方法，此时主线程需要等待（阻塞），
 * 等待子线程结束了(Waits for this thread to die.),才能继续执行t.join()之后的代码块
 */
public class Thread01 extends Thread {

    private static int RESULT = 0;

    public static void main(String[] args) throws InterruptedException {

        System.out.println("当前线程====" + Thread.currentThread().getName());

        long start = System.currentTimeMillis();

        //创建一个线程，并进行阻塞
        Thread myThread = new Thread01();
        myThread.start();
        myThread.join();

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
