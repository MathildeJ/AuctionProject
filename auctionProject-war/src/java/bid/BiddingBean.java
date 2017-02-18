package bid;

import auction.BidManager;
import auction.Bidding;
import auction.BidManagerBean;
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
    
   
    public BidManager getBidManager() {
        return bidManager;
    }

    public void setBidManager(BidManager bean) {
        this.bidManager = bean;
    }


    public void addBidding(){
        Bidding bidding = new Bidding();
        bidManager.addBid(bidding);
    }
    
    public List<Bidding> allBidding(){
        // return bidManager.getBid(item)
        return null;
    }

}
