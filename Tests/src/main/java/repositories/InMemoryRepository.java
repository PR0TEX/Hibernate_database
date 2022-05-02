package repositories;

import java.util.*;

public abstract class InMemoryRepository<E extends Comparable<E>> {
    protected Set<E> set;

    public InMemoryRepository() { set = new HashSet<>(); }

    public InMemoryRepository(boolean sorted) { set = sorted ? new TreeSet<>() : new HashSet<>(); }

    public InMemoryRepository(Comparator<E> comparator){
        set = new TreeSet<>(comparator);
    }

    public List<E> findAll() { return new ArrayList<>(set); }

    public void delete(E entity) { set.remove(entity); }

    public void add(E entity) {
        set.add(entity);
    }

}
