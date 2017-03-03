/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package auction;

import javax.ejb.Local;

/**
 *
 * @author x0103713
 */
@Local
public interface TimeManager {
    public void updateItemStatus();
    public void updatePersonStatus();
}
