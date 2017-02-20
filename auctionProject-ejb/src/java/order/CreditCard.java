/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package order;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Embeddable;

/**
 *
 * @author Mathilde
 */
@Embeddable
public class CreditCard implements Serializable {
        
        @Column(name = "CREDIT_CARD_TYPE")
        private String cctype;
        
        @Column(name = "CREDIT_CARD_NUMBER")
        private long ccNumber;

        public CreditCard() {
        }

        public CreditCard(String cctype, long ccNumber) {
            this.cctype = cctype;
            this.ccNumber = ccNumber;
        }
        

        public String getCctype() {
            return cctype;
        }

        public void setCctype(String cctype) {
            this.cctype = cctype;
        }

        public long getCcNumber() {
            return ccNumber;
        }

        public void setCcNumber(long ccNumber) {
            this.ccNumber = ccNumber;
        }
   
        @Override
        public String toString() {
            return "creditCard[" + cctype + " "+ ccNumber +"]";
        }
        
    }
