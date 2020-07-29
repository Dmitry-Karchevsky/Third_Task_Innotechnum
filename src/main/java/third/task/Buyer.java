package third.task;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;

public class Buyer extends Thread{
    private int products = 0;
    private int operations = 0;
    //private static boolean availabilityOfProducts = true;
    //private static boolean sum = 1;
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

    // каждый покупатель смотрит на количество товаров на складе
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

    // покупатель смотрит на переменную availabilityOfProducts, отвечающую за наличие товаров на складе
    /*@Override
    public void run() {
        int tempProductsInOneBuyer;
        while (availabilityOfProducts){
            try {
                barrier.await();
                tempProductsInOneBuyer = Store.takeProducts(getRandomCount());
                if (tempProductsInOneBuyer != 0) {
                    addProducts(tempProductsInOneBuyer);
                    operations++;
                }
                else availabilityOfProducts = false;
                barrier.await();
            } catch (InterruptedException | BrokenBarrierException e) {
                e.printStackTrace();
            }
        }
    }*/

    // смотрится сумма товаров у всех покупателей за один поход на склад
    /*@Override
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
    }*/
}
