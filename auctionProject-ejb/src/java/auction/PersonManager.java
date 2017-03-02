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
 * @author Mathilde
 */
@Local
public interface PersonManager {
    public void addPerson(String firstName, String lastName);
    public void addPerson(String firstName, String lastName, String address);
    public List<Person> listPersons();
    public Person findPerson(Long id);
    public List<Person> searchByPersonName(String firstName);
}
