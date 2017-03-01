/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package auction;

import java.util.Date;
import java.util.Iterator;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

/**
 * Status Values
 * Value    Label      Description 
 * -1       Error      Something went wrong with the item.
 *  0       New        Item just created and the start date is in the future
 *  1       Open       Item open for biddings, start date in the pass end date in the future.
 *  2       Closed     Item's auction finished, end date in the pass.
 *  3       Ordered    Item ordered by the buyer.
 *  4       Canceled   Item canceled by the buyer.
 *  5       Removed    Item removed from auction by the seller.
 *
 */


/**
 *
 * @author Mathilde
 */
@Stateless
public class ItemManagerBean implements ItemManager {

    @PersistenceContext(unitName="auctionProject-ejbPU")
    private EntityManager em;
    
    @Override
    public void addItem(String name, String description, double startPrice, Date startDate, Date endDate, Long id, List<String> categoriesId) {
        if(endDate.compareTo(startDate)>0){
            Item i = new Item(name, description, startPrice, startDate, endDate);
            try{
                addPerson(i, id);
                addCategory(i, categoriesId);
                em.persist(i);
            } catch(Exception ex){
                ex.printStackTrace();
            }
        } else {
            System.out.println("End date cannot be before start date.");
        }
    }
    
    @Override
    public List<Item> listItems(){
       Query query = em.createNamedQuery("Item.listAll");
       return (List<Item>) query.getResultList();
    }
    
     @Override
     public void addPerson(Item item, Long id) throws Exception{
            Person person = em.find(Person.class, id);
            if(person != null){
                item.setPerson(person);
                em.merge(item);
            } else {
                throw new Exception();
            }    
     }
     
    @Override
    public void addCategory (Item item, List<String> cat){
            Iterator<String> catIterator = cat.iterator(); 
            while (catIterator.hasNext()) {
                Category category = em.find(Category.class, catIterator.next());          
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
    
    @Override
    public List<Item> searchByName(String name) {
        Query query = em.createNamedQuery("Item.searchByName");
        query.setParameter("name", name);
        return (List<Item>) query.getResultList();
    }
    
}
