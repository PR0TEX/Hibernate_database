package repositories;

import entities.Mage;
import entities.Tower;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Query;
import java.util.List;

public class MageRepository extends JPArepository<Mage, String>{
    public MageRepository(EntityManagerFactory emf){
        super(emf, Mage.class);
    }
    public List<Mage> findAllGreaterThan(int level){
        EntityManager em = getEmf().createEntityManager();
        List<Mage> list = em.createQuery("select m from Mage m where m.level >" + level,getEntityClass())
                .getResultList();
        em.close();
        return list;
    }
    public List<Mage> findAllGreaterThan(int level, Tower tower){
        EntityManager em = getEmf().createEntityManager();
        List<Mage> list = em.createQuery("select e from "
                + getEntityClass().getSimpleName() + " e where e.level >" + level +
                "AND tower = '" + tower.getName() +"'",getEntityClass())
                .getResultList();
        em.close();
        return list;
    }
    public List<Mage> findaGreaterThanMin(){
        EntityManager em = getEmf().createEntityManager();
        int minLevel = (int) em.createQuery("Select min(level) from Mage where tower IS NOT null")
                    .getSingleResult();
        List<Mage> list = em.createQuery("select m from Mage m where m.level >"+minLevel+" AND m.tower is not null",getEntityClass()).getResultList();
        em.close();
        return list;

    }
}
