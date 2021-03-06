/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package auction;

import java.util.Calendar;
import java.util.Date;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.Schedule;
import javax.ejb.Singleton;
import javax.ejb.Startup;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.ejb.EJB;

/**
 *
 * @author x0103713
 */
@Startup
@Singleton
public class TimeManagerBean implements TimeManager {
    @PersistenceContext(unitName="auctionProject-ejbPU")
    private EntityManager em;
    
    @EJB
    private ItemManager im;
    @EJB
    private PersonManager pm;
    @PostConstruct
    @Schedule(second="0", minute="*", hour="*", dayOfMonth="*", month="*", year="*")
    public  void updateItemStatus(){
        
        Date today = new Date();
        Calendar c= Calendar.getInstance();
        c.setTime(today);
        
        //For testing purposes.
//        c.add(Calendar.DAY_OF_MONTH, 2);
//        today = c.getTime();
//        c.setTime(today);
         
        System.out.println(c.getTime());
        c.add(Calendar.DAY_OF_MONTH, -2);
        c.set(Calendar.HOUR, 23);
        c.set(Calendar.MINUTE, 59);
        
        Date yesterday = c.getTime();
        System.out.println("Date of yesterday" + yesterday );
        c.set(Calendar.HOUR, 0);
        c.set(Calendar.MINUTE, 0);
        c.add(Calendar.DAY_OF_MONTH, 2);
        Date tomorrow = c.getTime();
        System.out.println("Date of tomorrow" + tomorrow );
        
        //Search for the open Items.
        List<Item> openItems = im.listItemByStatus(1);
        //Search for the new Items.
        List<Item> newItems = im.listItemByStatus(0);
        for(Item i:openItems){
            if ( i.getEndDate().before(tomorrow)){
                // Set the status of the item to close.    
                i.setStatus(2);
                em.merge(i);
                System.out.println("Closing Bidding");
            }
        }
        
        for(Item i:newItems){
            
            if ( i.getStartDate().after(yesterday)){
                
                if ( i.getStartDate().before(tomorrow)){
                    // Set the status of the item to open.
                    i.setStatus(1);
                    em.merge(i);
                    System.out.println("New items for bidding");
                }
            }
        }
        
    } 
    
    @Schedule(second="0", minute="0", hour="*", dayOfMonth="*", month="*", year="*")
    public  void updatePersonStatus(){

        //Search for persons with three cancelations
        List<Person> punishedList = pm.searchByCancelCounter(3);
        
        for(Person p: punishedList){
            p.setCancelCounter(0);
            em.merge(p);
            System.out.println("Allowing  User to bid again.");
        }     
    } 
    // Add business logic below. (Right-click in editor and choose
    // "Insert Code > Add Business Method")
}
