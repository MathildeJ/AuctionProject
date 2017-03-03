/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package auction;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Random;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.ejb.Lock;
import static javax.ejb.LockType.READ;
import javax.ejb.Schedule;
import javax.ejb.Singleton;
import javax.ejb.Startup;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

/**
 *
 * @author David
 */
@Singleton
@Startup
public class PromotionUpdateBean implements PromotionUpdate {
    final int NUMBER_OF_PROMOTIONS = 10;
    final int ITEMS_PER_PROMOTION = 3;
    final int STEP_FOR_DISCOUNTS = 15;
    
    List<Promotion> promotions;
   
    @PostConstruct
    @Schedule(second="0", minute="0", hour="*", dayOfMonth="*", month="*", year="*")
    public void updatePromotions() {
        System.out.println("Generating Promotions " + new Date());
        generatePromotions();
    }
    
    private void generatePromotions(){
        promotions = new ArrayList();
        for (int i = 0; i < NUMBER_OF_PROMOTIONS; i++) {
            promotions.add(getRandomPromotion());
        }
    }

    
    public List<Promotion> getPromotions() {
        return promotions;
    }

    private int randomPercentage(int step) {
        Random randomGenerator = new Random();
        int randomNumber;
        //ex/ if step = 5: random between 0-18 * 5 + 5 = 5,10,...,95
        randomNumber = randomGenerator.nextInt((100/step - 1))* step + step;//0 and 100 excluded
        return randomNumber;
    }
    
    private enum PROMOTION_MESSAGES {
        FREE_DELIVERY("Free Delivery"),
        VOUCHER("Voucher"),
        DISCOUNT("Discount");
        
        private String description;
        
        private PROMOTION_MESSAGES (String description){
            this.description = description;
        }
        
        public String getDescription() {
            return description;
        }

    }
    
    @EJB
     ItemManager im;
        
    @PersistenceContext(unitName="auctionProject-ejbPU")
    private EntityManager em;
        /**
         * 
         * @param itemsToSelect the amount of items to select.
         * @return A list with itemsToSelect random items. 
         * If items registered in database < itemsToSelect will return the whole item list.
         */
    List<Item> generateRandomItemList(int itemsToSelect) {
        List<Item> ans = new ArrayList();
        Query query = em.createNamedQuery("Item.listAll");
        List<Item> items = (List<Item>) query.getResultList();
        while (!items.isEmpty() && itemsToSelect-- > 0) {
            Item item = items.get(new Random().nextInt(items.size()));
            items.remove(item);
            ans.add(item);
        }
        return ans;
    }
    
     Promotion getRandomPromotion() {
         Random randomGenerator = new Random();
         int numberOfMessageOptions = PROMOTION_MESSAGES.values().length;
         String randomMessage = PROMOTION_MESSAGES.values()[randomGenerator.nextInt(numberOfMessageOptions)].getDescription();
        if (randomMessage.equals(PROMOTION_MESSAGES.DISCOUNT.getDescription())) {
            randomMessage = randomPercentage(STEP_FOR_DISCOUNTS) + "% " + randomMessage;
        }
         List<Item> randomItems = generateRandomItemList(ITEMS_PER_PROMOTION);
         return new Promotion (randomMessage, randomItems);
    }
    
    
    
    
}
