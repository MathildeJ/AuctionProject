/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package auction;

import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
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
    public Item addItem(String name, String description, double startPrice, Date startDate, Date endDate) {
        Item i = new Item(name, description, startPrice, startDate, endDate);
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
     
    @Override
    public void addCategory (Item item, List<String> cat){
            Iterator<String> catIterator = cat.iterator(); 
            while (catIterator.hasNext()) {
                Category category = em.find(Category.class, catIterator.next());
                List<Item> items = category.getItems();
                items.add(item);
                category.setItems(items);
                em.persist(category);
                List<Category> categories = item.getCategories();
                categories.add(category);
                item.setCategories(categories);
                em.merge(item);
            }
    }
    
    @Override
    public List<Item> listItemUserStatus(long searchUser, int searchStatus){
       Person person = em.find(Person.class, searchUser);
       Query query = em.createNamedQuery("Item.listUserStatus");
       query.setParameter("person", person);
       query.setParameter("status", searchStatus);
       return (List<Item>) query.getResultList();
    }
    
    @Override
    public List<Item> listItemByStatus(int searchStatus){
       Query query = em.createNamedQuery("Item.listByStatus");
       query.setParameter("status", searchStatus);
       return (List<Item>) query.getResultList();
    }
    
    @Override
    public Item findItem(Long id){
        Item item = em.find(Item.class, id);
        return item;
    }
    
}
