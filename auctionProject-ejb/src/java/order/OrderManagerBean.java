/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package order;

import auction.Item;
import auction.ItemManager;
import auction.Person;
import java.util.List;
import java.util.concurrent.Future;
import javax.annotation.Resource;
import javax.ejb.AsyncResult;
import javax.ejb.Asynchronous;
import javax.ejb.EJB;
import javax.ejb.Stateful;
import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.jms.ConnectionFactory;
import javax.jms.JMSContext;
import javax.jms.Queue;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

/**
 *
 * @author Mathilde
 */
@Stateless
//@Stateful
public class OrderManagerBean implements OrderManager {

    @PersistenceContext(unitName="auctionProject-ejbPU")
    private EntityManager em;
    
    @EJB
    ItemManager im;
    
    @Resource(mappedName = "OrderRequestQueue")
    private Queue orderRequestQueue;

    @Inject
    JMSContext context;
    
    @Resource(lookup="jms/OrderConnectionFactory")
    private ConnectionFactory connectionFactory;
    
    private OrderI lastOrder;

    @Override
    public OrderI addOrder(Person winner, Item item, String shippingAddress, String cctype, long ccnumber) {
        OrderI order = new OrderI(winner, item, shippingAddress);
        CreditCard cc = new CreditCard(cctype, ccnumber);
        order.setCreditCard(cc);
        if(checkOrder(order) && newOrder(order)){
            order.setStatus(0);
            em.persist(order);
            lastOrder = order;
            return order;
        } else {
            order.setStatus(-1);
            throw new IllegalArgumentException("You can't order this item! The item is invalid or you didn't win it.");
        }
    }

    public ItemManager getIm() {
        return im;
    }

    public void setIm(ItemManager im) {
        this.im = im;
    }

    public EntityManager getEm() {
        return em;
    }

    public void setEm(EntityManager em) {
        this.em = em;
    }

    @Override
    public List<OrderI> listOrders(){
       Query query = em.createNamedQuery("OrderI.listAll");
       return (List<OrderI>) query.getResultList();
    }
  
    @Override
    public List<Item> listItems(){
       Query query = em.createNamedQuery("OrderI.listItems");
       return (List<Item>) query.getResultList();
    }
    
    @Override
    public List<OrderI> listOrdersByPerson(long personId){
       Person person = em.find(Person.class, personId);
       Query query = em.createNamedQuery("OrderI.listByPerson");
       query.setParameter("person", person);
       return (List<OrderI>) query.getResultList();
    }
    
    @Override
    public List<OrderI> listOrdersByPersonStatus(long personId, int searchStatus){
       Person person = em.find(Person.class, personId);
       Query query = em.createNamedQuery("OrderI.listByPersonStatus");
       query.setParameter("person", person);
       query.setParameter("status", searchStatus);
       return (List<OrderI>) query.getResultList();
    }

    @Override
    public Boolean checkOrder(OrderI order){
        List<Item> items = im.listItemByStatus(3);// check that item status is "OVER"/"WON"
        //should also check if the person who is placing the order won the bid on this item : search bids by item, check if last one is from person
        return(items.contains(order.getItem()));
    }
    
    @Override
    // check if order has already been placed
    //could be removed if status of item was changed when it is ordered
    public Boolean newOrder(OrderI order){
        List<Item> orderedItems = listItems();
        return(!orderedItems.contains(order.getItem()));
    }
    
    @Override
    public void sendOrder(){ 
        //testing with empty order
        //OrderI order = new OrderI();
        //System.out.println(context);
        //context.createProducer().send(orderRequestQueue, order);
        
        //send last order placed
        context.createProducer().send(orderRequestQueue, lastOrder);
        System.out.println("Your order has been sent.");
    }
    
    @Override
    @Asynchronous
    public Future<String> checkCreditCard(){
        String response = "";
        if(lastOrder.getCreditCard().getCctype().equals("Visa")){
            response = "Credit Card OK";
        } else {
            throw new IllegalArgumentException("Invalid credit card type (must be a Visa)");
        }
        return new AsyncResult<String>(response);
    }
    
    @Override
    public void updateOrder(OrderI order){
        order.setStatus(1);
        em.merge(order);
        lastOrder = null;
    }
}
