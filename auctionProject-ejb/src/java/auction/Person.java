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
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

/**
 *
 * @author Mathilde
 */
@Entity
@NamedQueries({
    @NamedQuery(name="Person.listAll", query = "SELECT p FROM Person p"),
    @NamedQuery(name="Person.searchByfirstName", 
            query = "SELECT p FROM Person p WHERE p.firstName = :firstname"),
    @NamedQuery(name="Person.searchByCancelCounter", 
            query = "SELECT p FROM Person p WHERE p.cancelCounter = :times")
        
})
@Table(name = "ROOT.PERSON")
public class Person implements Serializable {
    @SequenceGenerator(name="seq",sequenceName="seqseq")
    
    private static final long serialVersionUID = 1L;
    @Id
    @Column(name = "ID") @GeneratedValue(strategy=GenerationType.AUTO, generator="seq")
    private Long id;

    @Column(name = "firstName")
    private String firstName;
    
    @Column(name = "familyName")
    private String familyName;
    
    @Column(name = "address")
    private String address;
    
    @OneToMany(targetEntity=Bidding.class, mappedBy="person")
    private List<Bidding> biddings;
    
    @OneToMany(targetEntity=Item.class, mappedBy="person")
    private List<Item> items;
    
    @Column (name = "cancelCounter")
    private int cancelCounter;
    
    public Person() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getFamilyName() {
        return familyName;
    }

    public void setLastName(String familyName) {
        this.familyName = familyName;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public List<Bidding> getBiddings() {
        return biddings;
    }

    public void setBiddings(List<Bidding> biddings) {
        this.biddings = biddings;
    }

    public Person(String firstName, String familyName) {
        this.firstName = firstName;
        this.familyName = familyName;
    }
    
    public Person(String firstName, String familyName, String address){
        this(firstName, familyName);
        this.address = address;
    }

    public List<Item> getItems() {
        return items;
    }

    public void setItems(List<Item> items) {
        this.items = items;
    }
    
    @Override
    public String toString() {
        return "auction.Person[ id=" + id + " ]";
    }

    /**
     * @return the cancelCounter
     */
    public int getCancelCounter() {
        return cancelCounter;
    }

    /**
     * @param cancelCounter the cancelCounter to set
     */
    public void setCancelCounter(int cancelCounter) {
        this.cancelCounter = cancelCounter;
    }
    
}

/*
import java.io.Serializable;
import java.util.List;
import java.util.Set;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@NamedQuery(name="Person.listAll", query = "SELECT p FROM Person p")
@Table(name = "APP.PERSONS")
public class Person implements Serializable {

    private String firstName;
    private String lastName;
    @Id
    private String nickName;
    private String birthDate;
    @ManyToOne
    @JoinColumn(name="STATUS_FULLTITLE", referencedColumnName="FULLTITLE")
    private Status status;
    @OneToMany(targetEntity=Address.class, mappedBy="person")
    //@Basic(fetch=FetchType.EAGER)
    //private Set<Address> addresses;
    private List<Address> addresses;

    public Person() {
    }

    public List<Address> getAddresses() {
        return addresses;
    }

    public void setAddresses(List<Address> addresses) {
        this.addresses = addresses;
    }
    

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public String getNickName() {
        return nickName;
    }

    public Person(String firstName, String lastName, String nickName) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.nickName = nickName;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(String birthDate) {
        this.birthDate = birthDate;
    }

    @Override
    public String toString() {
        return "Person: nickname[ =" + nickName + ", firstname ="+ firstName + ", lastname ="+ lastName + ", birthdate ="+ birthDate + ", status ="+ status + "]";
    }
*/