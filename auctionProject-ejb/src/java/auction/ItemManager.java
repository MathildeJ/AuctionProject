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
    
    public Item addItem(String name, String description, double startPrice,
            Date startDate, Date endDate);
    public List<Item> listItems();
    public void addPerson(Item item, Long id);
    public void addCategory (Item item, List<String> cat);
    public List<Item> listItemUserStatus(long searchUser, int searchStatus);
    
}
