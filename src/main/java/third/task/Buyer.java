package third.task;

public class Buyer extends Thread{
    private int products = 0;
    private int operations = 0;
    private final Store store;
    private static int buyers = 0;
    private static int avarageOp = 0;

    public Buyer(Store store) {
        this.store = store;
        buyers++;
    }

    public void addProducts(int count){
        products += count;
    }

    public int getProducts() {
        return products;
    }

    public int getOperations() {
        return operations;
    }

    public static int getRandomCount() {
        return (int) (Math.random() * 10) + 1;
    }

    /*@Override
    public void run() {
        while (store.getProducts() > 0) {
            int a = 0;
            synchronized (store) {
                if (store.getProducts() > 0 && operations <= getAvarageOp() / buyers) {
                    a = store.takeProducts(getRandomCount());
                    operations++;
                    avarageOp++;
                }
            }
            addProducts(a);
        }
    }*/

    @Override
    public void run() {
        while (store.getProducts() > 0) {
            synchronized (store) {
                if (store.getProducts() > 0 && operations <= avarageOp / buyers) {
                    addProducts(store.takeProducts(getRandomCount()));
                    operations++;
                    avarageOp++;
                }
            }
        }
    }
}
