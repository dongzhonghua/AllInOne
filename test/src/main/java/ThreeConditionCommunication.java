//编写程序实现,子线程循环3次,接着主线程循环5次,接着再子线程循环3次,主线程循环5次,如此反复,循环3次.

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class ThreeConditionCommunication {
    public static void main(String[] args) {

        final Business business = new Business();

        //创建并启动子线程老二
        new Thread(new Runnable() {
            @Override
            public void run() {
                for (int i = 1; i <= 5; i++) {
                    business.sub2(i);
                }
            }
        }).start();

        //创建并启动子线程老三
        new Thread(new Runnable() {
            @Override
            public void run() {
                for (int i = 1; i <= 5; i++) {
                    business.sub3(i);
                }
            }
        }).start();

        //主线程
        for (int i = 1; i <= 5; i++) {
            business.main(i);
        }
    }

    static class Business {

        Lock lock = new ReentrantLock();
        Condition condition1 = lock.newCondition();
        Condition condition2 = lock.newCondition();
        Condition condition3 = lock.newCondition();

        //定义一个变量来决定线程的执行权
        private int ShouldSub = 1;

        public void sub2(int i) {
            //上锁，不让其他线程执行
            lock.lock();
            try {
                if (ShouldSub != 2) { //如果不该老二执行，就等待
                    try {
                        condition2.await();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }

                for (int j = 1; j <= 2; j++) {
                    System.out.println("sub thread sequence of" + i + ",loop of " + j);
                }
                ShouldSub = 3;  //准备让老三执行
                condition3.signal();        //唤醒老三
            } finally {
                lock.unlock();
            }
        }

        public void sub3(int i) {

            lock.lock();
            try {
                if (ShouldSub != 3) {
                    try {
                        condition3.await();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }

                for (int j = 1; j <= 2; j++) {
                    System.out.println("sub2 thread sequence of" + i + ",loop of " + j);
                }
                ShouldSub = 1;  //准备让老大执行
                condition1.signal();        //唤醒老大
            } finally {
                lock.unlock();
            }
        }

        //主线程

        public void main(int i) {

            lock.lock();
            try {
                if (ShouldSub != 1) {
                    try {
                        condition1.await();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                for (int j = 1; j <= 3; j++) {
                    System.out.println("main thread sequence of" + i + ", loop of " + j);
                }
                ShouldSub = 2;  //准备让老二执行
                condition2.signal();        //唤醒老二
            } finally {
                lock.unlock();
            }
        }
    }
}
