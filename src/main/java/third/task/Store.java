package third.task;

public class Store {
    private static volatile int products = 100;

    public static synchronized int takeProducts(int count){
        if (count > products){
            count = products;
            products = 0;
        }
        else
            products -= count;
        //System.out.println(Thread.currentThread().getName());
        //System.out.println(products);
        return count;
    }

    public static int getProducts() {
        return products;
    }
}
