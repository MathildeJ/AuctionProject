/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package auction;

import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author Mathilde
 */
@Local
public interface ItemManager {
    
    public Item addItem(String name, String description, float startPrice, String startDate, String endDate, String status);
    public List<Item> listItems();
    public void addPerson(Item item, Long id);
    
}
