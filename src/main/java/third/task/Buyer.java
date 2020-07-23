package third.task;

public class Buyer extends Thread{
    private volatile int products = 0;
    private volatile static boolean isStoped = false;
    public volatile int ccc = 0;

    Store store;

    public Buyer(Store store) {
        this.store = store;
    }

    public synchronized void addProducts(int count){
        products += count;
    }

    public synchronized int getProducts() {
        return products;
    }

    public static int getRandomCount() {
        return (int) (Math.random() * 10) + 1;
    }

    @Override
    public void run() {
        while (Store.products > 0){
            addProducts(store.takeProducts(getRandomCount()));
            ccc++;
        }
    }
}
