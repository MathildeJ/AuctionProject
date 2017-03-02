package auction;

import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

/**
 *
 * @author Djamila BAROUDI (CHAIDA)
 */
@Stateless
public class BidManagerBean implements BidManager {

    @PersistenceContext(unitName = "auctionProject-ejbPU")
    private EntityManager em;

    public void persist(Object object) {
        em.persist(object);
    }
    
    @Override
    public void addBid(Bidding bid) {
        em.persist(bid);
    }
    
    @Override
    public void cancelBid(Bidding bid) {
        // Cancel bid
    }
    
    @Override
    public Bidding getBid(Long id){
        return em.find(Bidding.class, id);
    }

    @Override
    public List<Bidding> getBids(Item item) {
        // get list of bids
        Query query = em.createNamedQuery("Bidding.listByItemId");
       return (List<Bidding>) query.getResultList();
    }

    @Override
    public void deleteBid(Bidding bid) {
        em.remove(bid);
    }

    @Override
    public void updateBid(Bidding bid) {
        em.merge(bid);
    }
}
