/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package auction;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

/**
 *
 * @author Mathilde
 */
@Entity
public class Bidding implements Serializable {
    
    private static final long serialVersionUID = 1L;
    @Id
    @Column(name = "ID") @GeneratedValue(strategy=GenerationType.AUTO)
    private Long id;

    @Column(name = "biddingPrice")
    private String biddingPrice;
    
    @ManyToOne
    @JoinColumn(name="PERSON_ID", referencedColumnName="ID")
    private Person person;
    
    @ManyToOne
    @JoinColumn(name="ITEM_ID", referencedColumnName="ID")
    private Item item;


    public Bidding() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getBiddingPrice() {
        return biddingPrice;
    }

    public void setBiddingPrice(String biddingPrice) {
        this.biddingPrice = biddingPrice;
    }

    public Person getPerson() {
        return person;
    }

    public void setPerson(Person person) {
        this.person = person;
    }

    public Item getItem() {
        return item;
    }

    public void setItem(Item item) {
        this.item = item;
    }
    
    @Override
    public String toString() {
        return "auction.Bidding[ id=" + id + " ]";
    }
    
}
