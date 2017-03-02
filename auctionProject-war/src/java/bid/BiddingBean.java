package bid;

import auction.BidManager;
import auction.Bidding;
import auction.BidManagerBean;
import auction.Item;
import auction.ItemManager;
import auction.Person;
import auction.PersonManager;
import java.io.Serializable;
import java.util.List;
import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.inject.Named;

/**
 *
 * @author Djamila BAROUDI (CHAIDA)
 */
@Named
@SessionScoped
public class BiddingBean implements Serializable {

    @EJB
    private BidManager bidManager;
    
    @EJB
    private PersonManager personManager;
    
    @EJB
    private ItemManager itemManager;
    
    private Long personId = 0L;
    private Long itemId = 0L;
    private double biddingPrice;

    public double getBiddingPrice() {
        return biddingPrice;
    }

    public void setBiddingPrice(double biddingPrice) {
        this.biddingPrice = biddingPrice;
    }

    public Long getPersonId() {
        return personId;
    }

    public void setPersonId(Long personId) {
        this.personId = personId;
    }

    public Long getItemId() {
        return itemId;
    }

    public void setItemId(Long itemId) {
        this.itemId = itemId;
    }
    
    public BidManager getBidManager() {
        return bidManager;
    }

    public void setBidManager(BidManager bean) {
        this.bidManager = bean;
    }


    public void addBidding(){
        // Find the owner of the item
        Person person = personManager.findPerson(personId);
        Item item = itemManager.findItem(itemId);
        Bidding bidding = new Bidding();
        bidding.setItem(item);
        bidding.setPerson(person);
        bidding.setBiddingPrice(biddingPrice);
        bidManager.addBid(bidding);
    }
    
    public List<Bidding> allBidding(){
        Item item = itemManager.findItem(itemId);
        return bidManager.getBids(item);
    }

}
