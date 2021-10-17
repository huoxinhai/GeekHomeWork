package com.geek.homework.week04.v2;

/**
 *  使用 wait/notify 实现线程等待和通知
 * - 当线程执行wait()时，会把当前的锁释放，然后让出CPU，进入等待状态
 * - 当执行notify/notifyAll方法时，会唤醒一个处于等待该 对象锁 的线程，然后继续往下执行，
 * 直到执行完退出对象锁锁住的区域（synchronized修饰的代码块）后再释放锁。
 */
public class Thread03 extends Thread {
    private static int RESULT = 0;

    public static void main(String[] args) throws InterruptedException {

        System.out.println("当前线程====" + Thread.currentThread().getName());

        long start = System.currentTimeMillis();

        //创建一个线程，并进行阻塞
        Thread03 myThread = new Thread03();
        myThread.start();

        System.out.println("异步计算结果为：" + myThread.getResult());

        System.out.println("使用时间：" + (System.currentTimeMillis() - start) + " ms");
    }

    private int getResult() throws InterruptedException {
        synchronized (this) {
            if (RESULT == 0) {
                // 未赋值，就阻塞住
                wait();
            }
        }
        return RESULT;
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
        synchronized (this) {
            RESULT = sum();
            // 唤醒主线程
            notifyAll();
        }
    }
}
