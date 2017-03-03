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
 * @author etudiant
 */
@Local
public interface ShoppingCar {
    public List<Item> getItems();
    public void addToShoppingCar(Item item);
}
