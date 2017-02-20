/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package order;

import auction.Item;
import auction.ItemManager;
import auction.Person;
import auction.PersonManager;
import java.io.Serializable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;
import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.inject.Named;

/**
 *
 * @author Mathilde
 */
@Named
@SessionScoped
public class OrderBean implements Serializable {
    @EJB
    private OrderManager om;
    
    @EJB
    private PersonManager pm;
    
    @EJB
    private ItemManager im;

    private long personID;
    private long itemID;
    private String address;
    private String CCType;
    private long CCNumber;
    
    private CreditCard creditCard;

    public OrderBean(){    
        
    }
    
    public OrderManager getOm() {
        return om;
    }

    public void setOm(OrderManager om) {
        this.om = om;
    }

    public long getPersonID() {
        return personID;
    }

    public void setPersonID(long personID) {
        this.personID = personID;
    }

    public long getItemID() {
        return itemID;
    }

    public void setItemID(long itemID) {
        this.itemID = itemID;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public PersonManager getPm() {
        return pm;
    }

    public void setPm(PersonManager pm) {
        this.pm = pm;
    }

    public ItemManager getIm() {
        return im;
    }

    public void setIm(ItemManager im) {
        this.im = im;
    }

    public CreditCard getCreditCard() {
        return creditCard;
    }

    public void setCreditCard(CreditCard creditCard) {
        this.creditCard = creditCard;
    }

    public String getCCType() {
        return CCType;
    }

    public void setCCType(String CCType) {
        this.CCType = CCType;
    }

    public long getCCNumber() {
        return CCNumber;
    }

    public void setCCNumber(long CCNumber) {
        this.CCNumber = CCNumber;
    }

    
    public OrderI placeOrder(){
        Person person = pm.findPerson(personID);
        Item item = im.findItem(itemID);
        OrderI o = om.addOrder(person, item, address, CCType, CCNumber);
        return o;
    }
    
    public void confirmOrder(){
        Future<String> status;
        String order;
        om.sendOrder();
        try{
          status = om.checkCreditCard();
          order = status.get();
        } catch(InterruptedException | ExecutionException ex){
        }
    }
}
