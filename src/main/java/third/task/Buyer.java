package third.task;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.Semaphore;

public class Buyer extends Thread{
    private int products = 0;
    private int operations = 0;

    private static CyclicBarrier barrier;
    private static Semaphore semaphore = new Semaphore(1, true);

    public static int count = 0;// просто для проверки

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

    //пока другие потоки не запустились один из них может прокрутиться в цикле несколько раз
    //нужно дождаться всех потоков
    @Override
    public void run() {
        try {
            //Ожидает пока все покупатели будут готовы совершать покупки (запустятся все потоки)
            barrier.await();
        } catch (InterruptedException | BrokenBarrierException e) {
            e.printStackTrace();
        }
        while (Store.getProducts() > 0) {
            count++;
            try {
                // Покупатели ждут когда один не возьмет определенное количесство товаров и не встанет в конец очереди
                semaphore.acquire();
                if (Store.getProducts() > 0) { // нужно что бы не добавлять нули
                    addProducts(Store.takeProducts(getRandomCount()));
                    operations++;
                }
                semaphore.release();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
