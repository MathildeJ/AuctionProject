/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package item;

import auction.Item;
import auction.ItemManager;
import java.io.Serializable;
import java.util.Date;
import java.util.List;
import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.inject.Named;

/**
 *
 * @author Mathilde
 */
@Named
//@RequestScoped
@SessionScoped
public class ItemBean implements Serializable {

    @EJB
    private ItemManager im;
    
    private String itemName;
    private String bidDescription;
    private double startPrice;
    private Date startDate;
    private Date endDate;
    private Long findID;
    private List<String> categoriesID;
    
    private Long searchUser;
    private int searchStatus;
    private String searchName;
    
    
    public ItemManager getIm() {
        return im;
    }

    public void setIm(ItemManager im) {
        this.im = im;
    }

    public String getItemName() {
        return itemName;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    public String getBidDescription() {
        return bidDescription;
    }

    public void setBidDescription(String bidDescription) {
        this.bidDescription = bidDescription;
    }

    public double getStartPrice() {
        return startPrice;
    }

    public void setStartPrice(double startPrice) {
        this.startPrice = startPrice;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    public Long getFindID() {
        return findID;
    }

    public void setFindID(Long findID) {
        this.findID = findID;
    }

    public List<String> getCategoriesID() {
        return categoriesID;
    }

    public void setCategoriesID(List<String> categoriesID) {
        this.categoriesID = categoriesID;
    }

    public Long getSearchUser() {
        return searchUser;
    }

    public void setSearchUser(Long searchUser) {
        this.searchUser = searchUser;
    }

    public String getSearchName() {
        return searchName;
    }
    
    public void setSearchName(String searchName) {
        this.searchName = searchName;
    }
    
    public int getSearchStatus() {
        return searchStatus;
    }

    public void setSearchStatus(int searchStatus) {
        this.searchStatus = searchStatus;
    }
    
    public void addi(){
        Item i = im.addItem(itemName, bidDescription, startPrice, startDate, endDate);
        im.addPerson(i, findID);
        im.addCategory(i, categoriesID);
    }
    
    public List<Item> searchUserStatus(){
        return im.listItemUserStatus(searchUser, searchStatus);
    }
    
    public List<Item> allItems(){
        return im.listItems();
    }

    public List<Item> searchByName(String name) {
        return im.searchByName(name);
    }
    
}
