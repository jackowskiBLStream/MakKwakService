import java.util.concurrent.BlockingQueue;

/**
 * Class which creates producer who puts kebab on the counter
 */
public class Producer extends AValidate implements Runnable {
    protected BlockingQueue<Integer> queue = null;

    private int Y;
    private boolean isValid;

    /**
     * Set initial values of queue and cooked kebabs
     *
     * @param queue blocking queue
     * @param Y     amount of cooked kebabs
     */
    public Producer(BlockingQueue<Integer> queue, int Y) {
        this.Y = Y;
        this.queue = queue;
        isValid = validate() && validateN();
    }

    /**
     * Start new thread that puts kebabs on the counter
     */
    @Override
    public void run() {
        while (isValid) {
            try {
                putKebab();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

    }

    protected int putKebab() throws InterruptedException {
        if (MealCounter.kebabCount + Y > MealCounter.N) {
            System.out.println("Cook waits for waiter ");
            Thread.sleep(2000);
        } else {
            queue.put(Y);
            MealCounter.kebabCount += Y;
            System.out.println("Cook puts " + Y + " kebabs. There are " + MealCounter.kebabCount + " kebabs on the counter");

        }
        return MealCounter.kebabCount;
    }

    /**
     * Method that validates amount of cooked kebabs which are to put on counter
     *
     * @return boolean value of amount of cooked kebabs
     */
    @Override
    public boolean validate() {
        return Y > 0 && Y <= MealCounter.N;
    }
}
