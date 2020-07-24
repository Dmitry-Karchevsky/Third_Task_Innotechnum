package third.task;

public class Store {
    private static int products = 1000;

    public static int takeProducts(int count){
        products -= count;
        if (products < 0) {
            count += products;
        }
        return count;
    }

    public static synchronized int getProducts() {
        return products;
    }
}
