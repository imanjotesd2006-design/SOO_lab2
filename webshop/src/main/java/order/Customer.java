
package order;

import eventbroker.EventPublisher;
import main.Main;

public class Customer extends EventPublisher implements Runnable {

    private String name;
    
    public Customer(String name){
        this.name = name;
    }
    @Override
    public void run() {
        for (int i = 0; i < Main.noOrders; i++) {
            buy("item-" + i);

        }
    }
    public void buy(String item){
        //System.out.println(name + " places an order for item "+item);
        publishEvent(new OrderEvent(name, item));
    }
}
