/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package auction;

import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

/**
 *
 * @author Mathilde
 */
@Stateless
public class PersonManagerBean implements PersonManager {
    @PersistenceContext(unitName="auctionProject-ejbPU")
    private EntityManager em;
    
    @Override
    public void addPerson(String firstName, String lastName) {
        Person p = new Person(firstName, lastName);
        em.persist(p);
        System.gc();
    }
    
    @Override
    public void addPerson(String firstName, String lastName, String address) {
        Person p = new Person(firstName, lastName, address);
        em.persist(p);
        System.gc();
    }
    
    @Override
    public List<Person> listPersons(){
       Query query = em.createNamedQuery("Person.listAll");
       return (List<Person>) query.getResultList();
    }
    
    @Override
    public Person findPerson(Long id){
        Person p = em.find(Person.class, id);
        return p;
    }
   /* public List<Person> searchByName(String firstName) {
        Query query = em.createNamedQuery("Person.searchByfirstName");
        query.setParameter("firstname", firstName);
        return (List<Person>) query.getResultList();
    }*/

    @Override
    public List<Person> searchByPersonName(String firstName) {
        Query query = em.createNamedQuery("Person.searchByfirstName");
        query.setParameter("firstname", firstName);
        return (List<Person>) query.getResultList();
    }
    
     @Override
    public List<Person> searchByCancelCounter(int times) {
        Query query = em.createNamedQuery("Person.searchByCancelCounter");
        query.setParameter("times", times);
        return (List<Person>) query.getResultList();
    }
    
    
}
