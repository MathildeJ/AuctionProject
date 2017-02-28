/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package auction;

import java.util.Date;
import javax.ejb.Schedule;
import javax.ejb.Stateless;
import javax.ejb.LocalBean;
import javax.ejb.Singleton;
import javax.ejb.Startup;

/**
 *
 * @author David
 */
@Singleton
@LocalBean
@Startup
public class Promotions {

//    @Schedule(dayOfWeek = "*", month = "*", hour = "0", dayOfMonth = "*", year = "*", minute = "0", second = "0")
//    public void myTimer() {
//        System.out.println("Timer event: " + new Date());
//    }
//
//    public enum PROMOTIONS {
//        FREE_DELIVERY("Free Delivery");
//        
//        private String description;
//        
//        public PROMOTIONS(final String description){
//            this.description = description;
//        }
//    }
}
