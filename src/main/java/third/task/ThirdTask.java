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
        System.out.println(Buyer.getRandomCount());

        while (Store.products > 0){ }

        for (Buyer b : list){
            b.interrupt();
            //b.stop();
        }

        int a = 0;
        for (Buyer b : list){
            a += b.getProducts();
            System.out.println(b.getProducts() + " | count = " + b.ccc);
        }
        System.out.println(a);
    }
}
