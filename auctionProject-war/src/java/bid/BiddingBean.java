/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bid;

import auction.Item;
import auction.ItemManager;
import auction.Person;
import auction.PersonManager;
import java.io.Serializable;
import java.util.List;
import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.inject.Named;

/**
 *
 * @author Mathilde
 */
@Named
//@RequestScoped
@SessionScoped
public class BiddingBean implements Serializable {

    @EJB
    private PersonManager pm;

    private String firstname;
    private String lastname;
    
    @EJB
    private ItemManager im;
    
    private String bidName;
    private String bidDescription;
    private float startPrice;
    private String startDate;
    private String endDate;
    private String status;
    private Long findID;
    
    public PersonManager getPm() {
        return pm;
    }

    public void setPm(PersonManager pm) {
        this.pm = pm;
    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public ItemManager getIm() {
        return im;
    }

    public void setIm(ItemManager im) {
        this.im = im;
    }

    public String getBidName() {
        return bidName;
    }

    public void setBidName(String bidName) {
        this.bidName = bidName;
    }

    public String getBidDescription() {
        return bidDescription;
    }

    public void setBidDescription(String bidDescription) {
        this.bidDescription = bidDescription;
    }

    public float getStartPrice() {
        return startPrice;
    }

    public void setStartPrice(float startPrice) {
        this.startPrice = startPrice;
    }

    public String getStartDate() {
        return startDate;
    }

    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }

    public String getEndDate() {
        return endDate;
    }

    public void setEndDate(String endDate) {
        this.endDate = endDate;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Long getFindID() {
        return findID;
    }

    public void setFindID(Long findID) {
        this.findID = findID;
    }
    
    public void addp(){
        //Person p = new Person(firstname,lastname);
        pm.addPerson(firstname,lastname);
    }
    
    public List<Person> allPersons(){
        return pm.listPersons();
    }
    
    public Person findPerson(){
        return pm.findPerson(findID);
    }
    
    public void addi(){
        Item i = im.addItem(bidName, bidDescription, startPrice, startDate, endDate, status);
        im.addPerson(i, findID);
    }
    
    public List<Item> allItems(){
        return im.listItems();
    }

}
