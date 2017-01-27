/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package auction;

import java.util.ArrayList;
import java.util.Date;
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
public class ItemManagerBean implements ItemManager {

    @PersistenceContext(unitName="auctionProject-ejbPU")
    private EntityManager em;
    
    @Override
    public Item addItem(String name, String description, double startPrice,
            Date startDate, Date endDate, int status) {
        Item i = new Item(name, description, startPrice, startDate, endDate, status);
        em.persist(i);
        System.gc();
        return i;
    }
    
    @Override
    public List<Item> listItems(){
       Query query = em.createNamedQuery("Item.listAll");
       return (List<Item>) query.getResultList();
    }
    
     @Override
     public void addPerson(Item item, Long id){
            Person person = em.find(Person.class, id);
            System.out.println(person);
            item.setPerson(person);
            em.merge(item);
            //List<Item> set = new ArrayList<Item>();
            //set.add(item); 
            List<Item> set = person.getItems();
            set.add(item);
            person.setItems(set);
            em.persist(person);      
     }
    
}
