/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package order;

import auction.Item;
import auction.Person;
import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

/**
 *
 * @author Mathilde
 */
@Entity
@NamedQueries({
    @NamedQuery(name="OrderI.listAll", 
            query = "SELECT o FROM OrderI o"),
    @NamedQuery(name="OrderI.listbyPerson",
            query="SELECT o FROM OrderI o WHERE o.winner = :person"), 
    @NamedQuery(name="Item.listbyPersonStatus",
            query="SELECT o FROM OrderI o WHERE o.winner = :person AND o.status = :status"),
    @NamedQuery(name="OrderI.listItems", 
            query = "SELECT o.item FROM OrderI o"),
}) 
public class OrderI implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Column(name = "ID")@GeneratedValue(strategy=GenerationType.TABLE)
    private Long id;
    
    @ManyToOne
    @JoinColumn(name="PERSON_ID", referencedColumnName="ID")
    private Person winner;
    
    @ManyToOne
    @JoinColumn(name="ITEM_ID", referencedColumnName="ID")
    private Item item;

    @Column(name = "SHIPPING_ADDRESS")
    private String shippingAddress;
    
    @Column(name = "CREDIT_CARD")
    private String creditCard;
    
    @Column(name = "STATUS")
    private int status;
    
    public OrderI(){
        
    }
    
    public OrderI(Person winner, Item item, String shippingAddress){
        this.winner = winner;
        this.item = item;
        this.shippingAddress = shippingAddress;
    }
    
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Person getWinner() {
        return winner;
    }

    public void setWinner(Person winner) {
        this.winner = winner;
    }

    public Item getItem() {
        return item;
    }

    public void setItem(Item item) {
        this.item = item;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getShippingAddress() {
        return shippingAddress;
    }

    public void setShippingAddress(String shippingAddress) {
        this.shippingAddress = shippingAddress;
    }

    public String getCreditCard() {
        return creditCard;
    }

    public void setCreditCard(String creditCard) {
        this.creditCard = creditCard;
    }
    
    @Override
    public String toString() {
        return "auction.Order[ id=" + id + ", person="+ winner +", item="+ item +", address="+ shippingAddress+", status="+ status +"]";
    }
    
}
