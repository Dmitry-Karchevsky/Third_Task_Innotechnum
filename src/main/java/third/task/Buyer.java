package third.task;

public class Buyer extends Thread{
    private int products = 0;
    private int operations = 0;
    private static int buyers = 0;
    private static int averageOperations = 0;

    public Buyer() {
        buyers++;
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

    @Override
    public void run() {
        while (Store.getProducts() > 0) {
            synchronized (Store.class) {
                if (Store.getProducts() > 0 && operations <= averageOperations / buyers) {
                    addProducts(Store.takeProducts(getRandomCount()));
                    operations++;
                    averageOperations++;
                }
            }
        }
    }
}
