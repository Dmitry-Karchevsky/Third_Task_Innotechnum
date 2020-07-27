package third.task;

public class Store {
    private static int products = 1000;

    public static int takeProducts(int count){
        if (count > products){
            count = products;
            products = 0;
        }
        else
            products -= count;
        //System.out.println(Thread.currentThread().getName());
        //System.out.println(count);
        return count;
    }

    public static int getProducts() {
        return products;
    }
}
