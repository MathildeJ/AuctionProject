/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bid;

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
public class PersonBean implements Serializable {

    @EJB
    private PersonManager pm;

    private String firstname;
    private String lastname;
    
   
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

    public void addp(){
        //Person p = new Person(firstname,lastname);
        pm.addPerson(firstname,lastname);
    }
    
    public List<Person> allPersons(){
        return pm.listPersons();
    }

}
