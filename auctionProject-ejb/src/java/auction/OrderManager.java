/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package auction;

import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author Mathilde
 */
@Local
public interface OrderManager {
    public OrderI addOrder(Person winner, Item item, String shippingAddress);
    public List<OrderI> listOrders();
    public List<Item> listItems();
    public List<OrderI> listOrdersByPerson(long personId);
    public List<OrderI> listOrdersByPersonStatus(long personId, int searchStatus);
    public Boolean checkOrder(OrderI order);
    public Boolean newOrder(OrderI order);
    
}
