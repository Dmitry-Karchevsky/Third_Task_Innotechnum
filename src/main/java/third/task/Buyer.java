package third.task;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;

public class Buyer extends Thread{
    private int products = 0;
    private int operations = 0;
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
        int tempProductsInOneBuyer;
        while (Store.getProducts() > 0){
            try {
                barrier.await();
                tempProductsInOneBuyer = Store.takeProducts(getRandomCount());
                if (tempProductsInOneBuyer != 0) {
                    addProducts(tempProductsInOneBuyer);
                    operations++;
                }
                barrier.await();
            } catch (InterruptedException | BrokenBarrierException e) {
                e.printStackTrace();
            }
        }
    }
}
