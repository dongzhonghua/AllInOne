import java.util.*;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * 消费者
 *
 * @author ctk
 */

class Consumer implements Runnable {
    private List<PCData> queue;

    public Consumer(List<PCData> queue) {
        this.queue = queue;
    }

    @Override
    public void run() {
        try {
            while (true) {
                if (Thread.currentThread().isInterrupted())
                    break;
                PCData data = null;
                synchronized (queue) {
                    if (queue.size() == 0) {
                        queue.wait();
                        queue.notifyAll();
                    }
                    data = queue.remove(0);
                }
                System.out.println(
                        Thread.currentThread().getId() + " 消费了:" + data.get() + " result:" + (data.get() * data.get()));
                Thread.sleep(1000);
            }

        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }

}


/**
 * 生产者
 *
 * @author MacBook
 */
class Producer implements Runnable {
    private List<PCData> queue;
    private int length;

    public Producer(List<PCData> queue, int length) {
        this.queue = queue;
        this.length = length;
    }

    @Override
    public void run() {
        try {
            while (true) {

                if (Thread.currentThread().isInterrupted())
                    break;
                Random r = new Random();
                long temp = r.nextInt(100);
                System.out.println(Thread.currentThread().getId() + " 生产了：" + temp);
                PCData data = new PCData();
                data.set(temp);
                synchronized (queue) {
                    if (queue.size() >= length) {
                        queue.notifyAll();
                        queue.wait();
                    } else
                        queue.add(data);
                }
                Thread.sleep(1000);
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }

}

public class ProduceAndConsumer {
    void a(int[] str){
        str[1] = 1;
    }
    public static void main(String[] args) {
        //List<PCData> queue = new ArrayList<PCData>();
        //int length = 10;
        //Producer p1 = new Producer(queue, length);
        //Producer p2 = new Producer(queue, length);
        //Producer p3 = new Producer(queue, length);
        //Consumer c1 = new Consumer(queue);
        //Consumer c2 = new Consumer(queue);
        //Consumer c3 = new Consumer(queue);
        //ExecutorService service = Executors.newCachedThreadPool();
        //service.execute(p1);
        //service.execute(p2);
        //service.execute(p3);
        //service.execute(c1);
        //service.execute(c2);
        //service.execute(c3);
        int[] a = new int[]{2,2,2,2,2};
        new ProduceAndConsumer().a(a);
        System.out.println(a);
        Map<String,String> map = new HashMap<>();
        map.put("a","a");
        map.put("b","b");
        map.put("c","c");
        map.put("d","d");
        Set<Map.Entry<String, String>> entries = map.entrySet();
        for (Map.Entry<String,String> entry:entries){
            String key = entry.getKey();
        }
    }
}

/**
 * 基本数据类型
 *
 * @author ctk
 */
class PCData {
    private long value;

    public void set(long value) {
        this.value = value;

    }

    public long get() {
        return value;
    }
}