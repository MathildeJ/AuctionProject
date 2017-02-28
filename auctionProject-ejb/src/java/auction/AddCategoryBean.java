/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package auction;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.Singleton;
import javax.ejb.Startup;
import javax.management.Query;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import org.apache.jasper.tagplugins.jstl.ForEach;

/**
 *
 * @author Poonam
 */
@Startup
@Singleton

public class AddCategoryBean implements AddCategory {
    @PersistenceContext(unitName="auctionProject-ejbPU")
    private EntityManager em;
    private Category c1 = new Category();
    private Category c2 = new Category();
    private Category c3 = new Category();
    private Category c4 = new Category();
    @Override
    @PostConstruct
    public void addCateg() {  
        c1.setName("Electronics");
        c2.setName("Home");
        c3.setName("Toys");
        c4.setName("Clothes");
//        ArrayList<String> cats = new ArrayList();
//        cats.add("Clothes");cats.add("Clothes1"); cats.add("Clothes2");      
//        for(int i=0;i<=2;i++){
//            Category category = new Category();        
//            category.setName(cats.get(i));
//            em.persist(category); 
        em.persist(c1);
        em.persist(c2);
        em.persist(c3);
        em.persist(c4);
        //}
    }
    @Override
    public List<Category> listCategoryValue(){     
       javax.persistence.Query query = em.createNamedQuery("Category.listAll");      
       return query.getResultList();
    }
}
