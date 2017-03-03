/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package auction;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import java.util.Objects;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Temporal;

/**
 *
 * @author Mathilde
 */
@Entity

@NamedQueries({
    @NamedQuery(name="Item.listAll", 
            query = "SELECT i FROM Item i"),
    @NamedQuery(name="Item.listUserStatus",
            query="SELECT i FROM Item i WHERE i.person = :person AND i.status = :status"),
    @NamedQuery(name="Item.listByStatus", 
            query = "SELECT i FROM Item i WHERE i.status = :status"),
    @NamedQuery(name="Item.searchByName", 
            query = "SELECT i FROM Item i WHERE i.name = :name"),
    @NamedQuery(name="Item.listOrderItem2", 
            query="SELECT i FROM Item i JOIN i.biddings b WHERE i.status = :status  AND b.person = :person AND b.biddingPrice = (SELECT max(t.biddingPrice) from Bidding t WHERE t.item.id = i.id GROUP BY t.item)"),
    @NamedQuery(name="Item.listTest", 
            query="SELECT max(t.biddingPrice) from Bidding t WHERE t.item.id = :item GROUP BY t.item"),
    @NamedQuery(name="Item.listOrderItem", 
            query="SELECT i FROM Item i WHERE i.person = :person AND i.status = :status AND i.startPrice=(select max(i.startPrice) from Item i group by i.name)"),
            // query="SELECT i FROM Item i WHERE i.person = :person AND i.status = :status AND  max(i.startPrice) group by i.name)"),

}) 

public class Item implements Serializable {
    @SequenceGenerator(name="sequ",sequenceName="item_seq")
    
    private static final long serialVersionUID = 1L;
    @Id
    @Column(name = "ID") @GeneratedValue(strategy=GenerationType.AUTO, generator="sequ")
    private Long id;

    @Column(name = "name")
    private String name;
    
    @Column(name = "description")
    private String description;
    
    @Column(name = "startPrice")
    private double startPrice;
    
    @Column(name = "startDate")
    @Temporal(javax.persistence.TemporalType.DATE)
    private Date startDate;
        
    @Column(name = "endDate")
    @Temporal(javax.persistence.TemporalType.DATE)
    private Date endDate;
    
    @Column(name = "status")
    private int status;
    
    @ManyToOne
    @JoinColumn(name="PERSON_ID", referencedColumnName="ID")
    private Person person;
    
    @OneToMany(targetEntity=Bidding.class, mappedBy="item")
    private List<Bidding> biddings;
    
    @ManyToMany(targetEntity=Category.class)
    private List<Category> categories;

    public Item() {
    }

    public Item(String name, String description, double startPrice, Date startDate, Date endDate) {
        this.name = name;
        this.description = description;
        this.startPrice = startPrice;
        this.startDate = startDate;
        this.endDate = endDate;
       
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public double getStartPrice() {
        return startPrice;
    }

    public void setStartPrice(double startPrice) {
        this.startPrice = startPrice;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }


    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public Person getPerson() {
        return person;
    }

    public void setPerson(Person person) {
        this.person = person;
    }

    public List<Category> getCategories() {
        return categories;
    }

    public void setCategories(List<Category> categories) {
        this.categories = categories;
    }

    public List<Bidding> getBiddings() {
        return biddings;
    }

    public void setBiddings(List<Bidding> biddings) {
        this.biddings = biddings;
    }
    

    @Override
    public String toString() {
        return name;
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 47 * hash + Objects.hashCode(this.id);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Item other = (Item) obj;
        if (!Objects.equals(this.id, other.id)) {
            return false;
        }
        return true;
    }
    
}
