/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package auction;

import java.util.Date;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author Mathilde
 */
@Local
public interface ItemManager {
    
    public void addItem(String name, String description, double startPrice,
            Date startDate, Date endDate, Long id, List<String> categoriesId);
    public List<Item> listItems();
    public void addPerson(Item item, Long id) throws Exception;
    public void addCategory (Item item, List<String> cat);
    public List<Item> listItemUserStatus(long searchUser, int searchStatus);
    public List<Item> listItemByStatus(int searchStatus);
    public Item findItem(Long id);
    public void updateStatus(Item item);
    public List<Item> searchByName(String name);
     public List<Item> searchByCategory( List<String> categories );
    public void removeItemByUser(long searchUser);
    public List<Item> listItemsForOrder(Long searchUser);
    public void removeItem(Item item);
    
}
