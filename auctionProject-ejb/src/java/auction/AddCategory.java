/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package auction;

import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author Poonam
 */
@Local
public interface AddCategory {
    public void addCateg();
    public List<Category> listCategoryValue();

}
