/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package auction;

import java.util.Arrays;
import java.util.List;

/**
 *
 * @author David
 */
public class Promotion {

    private String message;
    private List<Item> items;
    
    Promotion(String message, List<Item> items) {
        this.message = message;
        this.items = items;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public List<Item> getItems() {
        return items;
    }

    public void setItems(List<Item> items) {
        this.items = items;
    }
    
    @Override
    public String toString() {
        return message + " For the following Items: " + Arrays.toString(items.toArray());
    }
    
}
