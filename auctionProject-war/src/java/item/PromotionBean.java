/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package item;

import auction.Promotion;
import java.io.Serializable;
import java.util.List;
import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.inject.Named;
import auction.PromotionManager;
import auction.PromotionUpdate;
import java.util.ArrayList;
import javax.inject.Inject;


/**
 *
 * @author david
 */
@Named
@SessionScoped
public class PromotionBean implements Serializable{
    
    @EJB
    private PromotionManager promotionManager;
    
    public List<Promotion> getPromotions() {
        return promotionManager.getPromotions();
    }
    
}
