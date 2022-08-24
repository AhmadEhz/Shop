package shop.entity;

import java.util.ArrayList;

public class OrderList extends ArrayList<Order> {
    @Override
    public String toString() {
        String string = "";
        for (String s:(String[]) this.toArray())
            string += s + "\n";

        return string;
    }
}
