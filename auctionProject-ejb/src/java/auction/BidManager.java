package auction;
import java.util.List;
import javax.ejb.Local;
import javax.ejb.Remote;
/**
 *
 * @author Djamila BAROUDI (CHAIDA)
 */
@Local
/*
The @Local annotation on the BidService interface tells 
the container that the BidManager EJB can be accessed locally through the interface.
*/
public interface BidManager {
    public void addBid(Bidding bid);
    public void cancelBid(Bidding bid);
    public Bidding getBid(Long id);
    public void deleteBid(Bidding bid);
    public void updateBid(Bidding bid);
    public List<Bidding> getBids(Item item);
}
