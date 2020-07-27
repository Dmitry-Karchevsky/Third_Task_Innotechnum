package third.task;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;

public class Buyer extends Thread{
    private int products = 0;
    private int operations = 0;
    private static int sum = 1;
    private static CyclicBarrier barrier;

    public static void setCyclicBarrier(int buyers) {
        barrier = new CyclicBarrier(buyers);
    }

    private void addProducts(int count){
        products += count;
    }

    public int getProducts() {
        return products;
    }

    public int getOperations() {
        return operations;
    }

    private static int getRandomCount() {
        return (int) (Math.random() * 10) + 1;
    }

    @Override
    public void run() {
        while (sum != 0){
            try {
                barrier.await();
                sum = 0;
                barrier.await();
            } catch (InterruptedException | BrokenBarrierException e) {
                e.printStackTrace();
            }

            int tempProductsInOneBuyer = Store.takeProducts(getRandomCount());
            sum += tempProductsInOneBuyer;
            if (tempProductsInOneBuyer != 0) {
                addProducts(tempProductsInOneBuyer);
                operations++;
            }
        }
    }
}
