/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package auction;

import java.io.Serializable;
import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;

/**
 *
 * @author Mathilde
 */
@Entity
@NamedQuery(name="Item.listAll", query = "SELECT i FROM Item i")
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
    private float startPrice;
    
    @Column(name = "startDate")
    private String startDate;
        
    @Column(name = "endDate")
    private String endDate;
    
    @Column(name = "status")
    private String status;
    
    @ManyToOne
    @JoinColumn(name="PERSON_ID", referencedColumnName="ID")
    private Person person;
    
    @OneToMany(targetEntity=Bidding.class, mappedBy="item")
    private List<Bidding> biddings;
    
    @ManyToMany(targetEntity=Category.class)
    private List<Category> categories;

    public Item() {
    }

    public Item(String name, String description, float startPrice, String startDate, String endDate, String status) {
        this.name = name;
        this.description = description;
        this.startPrice = startPrice;
        this.startDate = startDate;
        this.endDate = endDate;
        this.status = status;
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
        return "auction.Item[ id=" + id + " ]";
    }
    
}
