package repositories;

import entities.Mage;
import entities.Tower;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import java.util.List;

public class TowerRepository extends JPArepository<Tower, String> {
    public TowerRepository(EntityManagerFactory emf) {
        super(emf, Tower.class);
    }

    public List<Tower> findAllLowerThan(int height) {
        EntityManager em = getEmf().createEntityManager();
        List<Tower> list = em.createQuery("select t from Tower t where t.height >" +height, getEntityClass())
                .getResultList();
        em.close();
        return list;
    }
}
