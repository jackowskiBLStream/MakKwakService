import java.util.concurrent.BlockingQueue;

/**
 * Class which creates customer who gets kebabs from the counter
 */
public class Consumer extends AValidate
        implements Runnable {
    protected BlockingQueue<Integer> queue = null;
    private boolean isValid;
    private int X;

    /**
     * Set initial values of queue and kebabs ready to serve
     *
     * @param queue blocking queue
     * @param X amount of kebabs to serve
     */
    public Consumer(BlockingQueue<Integer> queue, int X) {
        this.X = X;
        this.queue = queue;
        isValid = validate() && validateN();
    }

    /**
     * Start new thread that gets kebabs from counter
     */
    @Override
    public void run() {
        while (isValid) {
            try {
                getKebab();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

    }

    protected int getKebab() throws InterruptedException {
        if (X <= MealCounter.kebabCount) {
            Integer take = queue.take();

            MealCounter.kebabCount -= X;
            System.out.println("Waiter takes " + X + " kebabs. The are " + MealCounter.kebabCount + " kebabs on the counter" );
        } else {

            System.out.println("Waiter waits for kebabs");
            Thread.sleep(2000);
        }
        return MealCounter.kebabCount;


    }

    /**
     * Method that validates amount of kebabs to serve
     *
     * @return boolean value of amount of kebabs to serve
     */
    @Override
    boolean validate() {
        return X > 0 && X <= MealCounter.N;
    }
}
