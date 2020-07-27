package third.task;

import java.util.ArrayList;
import java.util.List;

public class ThirdTask {
    public static void main(String[] args) {
        int peoples = Integer.parseInt(args[0]);
        peoples = 3;
        List<Buyer> list = new ArrayList<>();
        Buyer.setCyclicBarrier(peoples);

        for (int i = 0; i < peoples; i++) {
            Buyer buyer = new Buyer();
            list.add(buyer);
            buyer.start();
        }



        for (Buyer b : list){
            try {
                b.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        int sumProducts = 0;
        int sumOperations = 0;
        for (Buyer b : list){
            sumProducts += b.getProducts();
            sumOperations += b.getOperations();
            System.out.println(b.getName() + " " + b.getProducts() + " | count = " + b.getOperations());
        }
        System.out.println("sumProducts = " + sumProducts + " | sumOperations = " + sumOperations);
        System.out.println("проходы потоков в цикле = " + Buyer.count);
    }
}
