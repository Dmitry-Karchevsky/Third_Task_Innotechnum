package third.task;

import java.util.ArrayList;
import java.util.List;

public class ThirdTask {
    public static void main(String[] args) {
        int peoples = Integer.parseInt(args[0]);
        Store store = new Store();

        List<Buyer> list = new ArrayList<>();
        for (int i = 0; i < peoples; i++) {
            Buyer buyer = new Buyer(store);
            list.add(buyer);
            buyer.start();
        }

        while (store.getProducts() > 0) {}

        for (Buyer b : list){
            b.interrupt();
        }

        int a = 0;
        for (Buyer b : list){
            a += b.getProducts();
            System.out.println(b.getProducts() + " | count = " + b.getOperations());
        }
        System.out.println(a);
    }
}
