/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package auction;

import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.Stateful;

/**
 *
 * @author David
 */
@Stateful
public class ShoppingCarBean implements ShoppingCar {

    private List<Item> shoppingCar;
    
    @PostConstruct
    public void init() {
        shoppingCar = new ArrayList();
    }
    
    @Override
    public List<Item> getItems() {
        return shoppingCar;
    }

    @Override
    public void addToShoppingCar(Item item) {
        if (!shoppingCar.contains(item)) {
            shoppingCar.add(item);
        }
    }
}
