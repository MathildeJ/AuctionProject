package auction;

import java.util.Date;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TemporalType;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Path;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

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
        em.merge(bid);
    }
    
    @Override
    public Bidding getBid(Long id){
        return em.find(Bidding.class, id);
    }

    @Override
    public List<Bidding> getBids() {
        // get list of bids
        Query query = em.createNamedQuery("Bidding.listAll");
       return (List<Bidding>) query.getResultList();
    }

    @Override
    public String deleteBid(Bidding bid) {
        em.getTransaction().begin();       
        em.remove(bid);
        em.getTransaction().commit();
        return null;
    }

    @Override
    public void updateBid(Bidding bid) {
        em.merge(bid);
    }

    @Override
    public List<Bidding> getBidsByDate(Date startDate, Date endDate) {
        Query query = em.createQuery("SELECT b " + "FROM Bidding b " + "JOIN b.item i WHERE i.startDate BETWEEN :start AND :end");
        query.setParameter("start", startDate, TemporalType.DATE);
        query.setParameter("end", endDate, TemporalType.DATE);
        return (List<Bidding>) query.getResultList();
    }
  
}
