package repositories;

import entities.Mage;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import java.util.List;

@Getter
public abstract class JPArepository <E,K>{
    @Getter
    private final EntityManagerFactory emf;
    @Getter
    private final Class<E> entityClass;

    public JPArepository(EntityManagerFactory emf, Class<E> entityClass){
        this.emf = emf;
        this.entityClass = entityClass;
    }
    public List<E> findAll(){
        EntityManager em = emf.createEntityManager();
        List<E> list = em.createQuery("select e from "+ entityClass.getSimpleName() + " e",entityClass).getResultList();
        em.close();
        return list;
    }
    public E find(K id){
        EntityManager em = emf.createEntityManager();
        E entity = em.find(entityClass, id);
        em.close();
        return entity;
    }
    public void delete(E entity){
        EntityManager em = emf.createEntityManager();
        EntityTransaction transaction = em.getTransaction();
        transaction.begin();
        em.remove(em.merge(entity));
        transaction.commit();
        em.close();
    }
    public void add(E entity){
        EntityManager em = emf.createEntityManager();
        EntityTransaction transaction = em.getTransaction();
        transaction.begin();
        em.persist(entity);
        transaction.commit();
        em.close();
    }
}
