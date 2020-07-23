package third.task;

public class Store {
    public static volatile int products = 1000;

    public synchronized int takeProducts(int count){
        System.out.println(products);
        products -= count;
        if (products < 0)
            count += products;
        return count;
    }

    public static synchronized int getProducts() {
        return products;
    }
}
