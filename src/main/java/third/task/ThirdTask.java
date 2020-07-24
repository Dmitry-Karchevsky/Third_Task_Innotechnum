package third.task;

import java.util.ArrayList;
import java.util.List;

public class ThirdTask {
    public static void main(String[] args) {
        int peoples = Integer.parseInt(args[0]);
        List<Buyer> list = new ArrayList<>();

        for (int i = 0; i < peoples; i++) {
            Buyer buyer = new Buyer();
            list.add(buyer);
            buyer.start();
        }

        while (Store.getProducts() > 0) {}

        for (Buyer b : list){
            b.interrupt();
        }

        int sum = 0;
        for (Buyer b : list){
            sum += b.getProducts();
            System.out.println(b.getProducts() + " | count = " + b.getOperations());
        }
        System.out.println(sum);
    }
}
