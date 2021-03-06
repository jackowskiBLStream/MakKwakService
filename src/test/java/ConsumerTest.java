import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

import static org.junit.Assert.*;

/**
 * Created by blstream on 3/7/2016.
 */
@Ignore
public class ConsumerTest {
    private Consumer consumer;
    private BlockingQueue queue;

    @Before
    public void setUp() throws Exception {

        MealCounter.kebabCount = 5;
        queue = new ArrayBlockingQueue(1024);


    }

    @Test
    public void testShouldReturnAmountOfKebabsAfterTakeFromCounter() throws Exception {
        //given
        MealCounter.N = 8;
        int givenAmountOfKebabs = 3;
        consumer = new Consumer(queue, 3);


        //when

        int amountOfKebabs = consumer.getKebab();

        //then
        assertEquals(givenAmountOfKebabs, amountOfKebabs);
    }
    @Test
    public void testShouldReturnAmountOfKebabsAfterMultiPutOnCounter() throws Exception {
        //given
        MealCounter.N = 8;
        int givenAmountOfKebabs = 6;
        consumer = new Consumer(queue, 3);

        //when

        consumer.getKebab();
        consumer.getKebab();
        int amountOfKebabs = consumer.getKebab();

        //then
        assertEquals(givenAmountOfKebabs, amountOfKebabs);
    }

    @Test
    public void testRunShouldReturnMaxAmountOfCookedKebabs() throws Exception {
        //given
        MealCounter.N = 8;
        int givenAmountOfKebabs = 6;
        consumer = new Consumer(queue, 3);
        Thread thread = new Thread(consumer);
        //when
        thread.start();
        Thread.sleep(100);
        //then
        assertEquals(givenAmountOfKebabs, MealCounter.kebabCount);
        thread.interrupt();




    }
    @Test
    public void testRunShouldReturnZeroWhenNegativeAmountOfCookedKebabs() throws Exception {
        //given
        MealCounter.N = 8;
        int givenAmountOfKebabs = 0;
        consumer = new Consumer(queue, -3);
        Thread thread = new Thread(consumer);
        //when
        thread.start();
        Thread.sleep(100);
        //then
        assertEquals(givenAmountOfKebabs, MealCounter.kebabCount);
        thread.interrupt();

    }
    @Test
    public void testRunShouldReturnZeroWhenNegativeAmountKebabsOnCounter() throws Exception {
        //given
        MealCounter.N = -8;
        int givenAmountOfKebabs = 0;
        consumer = new Consumer(queue, 3);
        Thread thread = new Thread(consumer);
        //when
        thread.start();
        Thread.sleep(100);
        //then
        assertEquals(givenAmountOfKebabs, MealCounter.kebabCount);
        thread.interrupt();

    }

    @Test
    public void testRunShouldReturnZeroWhenZeroCookedKebabs() throws Exception {
        //given
        MealCounter.N = 8;
        int givenAmountOfKebabs = 0;
        consumer = new Consumer(queue, 0);
        Thread thread = new Thread(consumer);
        //when
        thread.start();
        Thread.sleep(100);
        //then
        assertEquals(givenAmountOfKebabs, MealCounter.kebabCount);
        thread.interrupt();

    }
    @Test
    public void testRunShouldReturnZeroWhenZeroCapacityOfCounter() throws Exception {
        //given
        MealCounter.N = 0;
        int givenAmountOfKebabs = 0;
        consumer = new Consumer(queue, 3);
        Thread thread = new Thread(consumer);
        //when
        thread.start();
        Thread.sleep(100);
        //then
        assertEquals(givenAmountOfKebabs, MealCounter.kebabCount);
        thread.interrupt();

    }
    @Test
    public void testRunShouldReturnZeroWhenCookedKebabsGreaterThanCapacityOfCounter() throws Exception {
        //given
        MealCounter.N = 8;
        int givenAmountOfKebabs = 0;
        consumer = new Consumer(queue, 9);
        Thread thread = new Thread(consumer);
        //when
        thread.start();
        Thread.sleep(100);
        //then
        assertEquals(givenAmountOfKebabs, MealCounter.kebabCount);
        thread.interrupt();

    }

}