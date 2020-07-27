package third.task;

public class Store {
    private static int products = 1000;

    public static synchronized int takeProducts(int count){
        if (count > products){
            count = products;
            products = 0;
        }
        else
            products -= count;
        return count;
    }
}
