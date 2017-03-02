/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package auction;


import java.util.ArrayList;
import java.util.List;
import javax.ejb.EJB;

import javax.ejb.Stateless;
import javax.inject.Inject;

/**
 *
 * @author David
 */
@Stateless
public class PromotionManagerBean implements PromotionManager {
    
    @Inject
    PromotionUpdate promotionUpdate;
    
    @Override
    public List<Promotion> getPromotions() {
        return promotionUpdate.getPromotions();
    }

    
}
