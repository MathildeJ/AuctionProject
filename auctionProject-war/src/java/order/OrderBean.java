/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package order;

import auction.Item;
import auction.ItemManager;
import auction.OrderI;
import auction.OrderManager;
import auction.Person;
import auction.PersonManager;
import java.io.Serializable;
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
    
    public OrderI placeOrder(){
        Person person = pm.findPerson(personID);
        Item item = im.findItem(itemID);
        return om.addOrder(person, item, address);
    }

}
