/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package order;

import javax.annotation.Resource;
import javax.ejb.ActivationConfigProperty;
import javax.ejb.EJB;
import javax.ejb.MessageDriven;
import javax.ejb.MessageDrivenContext;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.ObjectMessage;

/**
 *
 * @author Mathilde
 */
@MessageDriven(name="testBean", activationConfig = {
    @ActivationConfigProperty(propertyName = "destinationType", propertyValue = "javax.jms.Queue"),
    @ActivationConfigProperty(propertyName = "destinationLookup", propertyValue = "OrderRequestQueue"),
})
public class OrderMessageBean implements MessageListener {
       
    @Resource
    private MessageDrivenContext mdctx;   
    
    @EJB
    OrderManager om;
    
    public OrderMessageBean() {
    }
    
    @Override
    public void onMessage(Message message) {
      ObjectMessage objectOrder = null;
      try {
         objectOrder = (ObjectMessage) message;
         OrderI order = (OrderI) objectOrder.getObject(); 
         System.out.println("order received:" + order);
         om.updateOrder(order);
 
      } catch (JMSException ex) {
         mdctx.setRollbackOnly();
      } 
    }
    
}
