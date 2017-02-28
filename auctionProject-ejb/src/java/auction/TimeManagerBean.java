/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package auction;

import java.util.Calendar;
import java.util.Date;
import java.util.List;
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
    
    @Schedule(second="0", minute="*", hour="*", dayOfMonth="*", month="*", year="*")
    public  void updateItemStatus(){
        
        Date date = new Date();
        Calendar c= Calendar.getInstance();
        c.setTime(date);
        System.out.println("test1");
        //Search for the open Items.
        List<Item> openItems = im.listItemByStatus(1);
        //Search for the new Items.
        List<Item> newItems = im.listItemByStatus(0);
        for(Item i:openItems){
            if ( i.getEndDate().before(date)){
                // Set the status of the item to close.    
                i.setStatus(2);
                em.merge(i);
                System.out.println("WE found some old dates");
            }
        }
        
        for(Item i:newItems){
            c.add(Calendar.DAY_OF_MONTH, -1);
            if ( i.getEndDate().after(c.getTime())){
                c.add(Calendar.DAY_OF_MONTH, 2);
                if ( i.getEndDate().before(c.getTime())){
                    // Set the status of the item to open.
                    i.setStatus(1);
                    em.merge(i);
                    System.out.println("New items for bidding");
                }
            }
        }
        
    } 
    // Add business logic below. (Right-click in editor and choose
    // "Insert Code > Add Business Method")
}
