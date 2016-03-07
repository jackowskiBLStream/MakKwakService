import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

/**
 * Created by blstream on 3/7/2016.
 */
public class Main {

    public static void main(String[] args) throws InterruptedException {

        MealCounter.N = Integer.parseInt(args[0]);
        BlockingQueue<Integer> queue = new ArrayBlockingQueue<Integer>(1024);


        Producer producer = new Producer(queue, Integer.parseInt(args[2]));
        Consumer consumer = new Consumer(queue, Integer.parseInt(args[1]));

        Thread consumerThread = new Thread(consumer);
        Thread producerThread = new Thread(producer);

        consumerThread.setName("CONSUMER");
        producerThread.setName("PRODUCER");
        producerThread.start();
        consumerThread.start();
        /*new Thread(producer).start();
        new Thread(consumer).start();*/


    }
}
