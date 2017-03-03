package bid;

import auction.BidManager;
import auction.Bidding;
import auction.BidManagerBean;
import auction.Item;
import auction.ItemManager;
import auction.Person;
import auction.PersonManager;
import java.io.Serializable;
import java.util.Date;
import java.util.LinkedList;
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

    public boolean isPersonCorrect() {
        if (personId == null) personId = -1L;
        return personManager.findPerson(personId) != null;
    }

    public boolean isBiddingPriceCorrect() {
        Item item = itemManager.findItem(itemId);
        if (item != null) {
            return item.getStartPrice() < biddingPrice;
        } else {
            return false;
        }
    }

    public boolean isItemCorrect() {
        if (itemId == null) itemId = -1L;
        return itemManager.findItem(itemId) != null;
    }

    public void addBidding() {
        // Find the owner of the item
        Person person = personManager.findPerson(personId);
        System.out.println(person.getFamilyName());
        Item item = itemManager.findItem(itemId);
        System.out.println(item.getName());
        // Checking the auction is open for the item.
        if (item.getStatus() == 1){
            Bidding bidding = new Bidding();
            bidding.setItem(item);
            bidding.setPerson(person);
            bidding.setBiddingPrice(biddingPrice);
            bidManager.addBid(bidding);
        }
    }

    public List<Bidding> allBidding() {
        return bidManager.getBids();
    }
    
    public List<Bidding> allWinningBiddding(){
       List<Bidding> listWinningBids = new LinkedList<>();
       for (Bidding bidding : bidManager.getBids()){
           Item item = bidding.getItem();
           if (item.getStartDate().after(new Date())){
               continue;
           }
           if(item.getEndDate().before(new Date())){
               listWinningBids.add(bidding);
           }
       }
       return listWinningBids;
    }
    public void deleteBidding(Bidding bidding){
        bidManager.deleteBid(bidding);
    }

    public void cancelBidding(Bidding bidding){
        bidManager.cancelBid(bidding);
    }
}
