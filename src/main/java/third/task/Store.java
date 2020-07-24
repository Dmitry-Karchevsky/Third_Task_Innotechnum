package third.task;

public class Store {
    private int products = 1000;

    public int takeProducts(int count){
        products -= count;
        if (products < 0) {
            count += products;
        }
        return count;
    }

    public synchronized int getProducts() {
        return products;
    }
}
