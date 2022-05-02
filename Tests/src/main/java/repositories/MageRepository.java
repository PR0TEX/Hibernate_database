package repositories;

import java.util.Collection;
import java.util.Optional;
import entities.Mage;

public class MageRepository extends repositories.InMemoryRepository<Mage> {
    private final Collection<Mage> collecion = set;
    public Optional<Mage> find(String name){
        for(Mage mage:collecion){
            if(mage.getName().equals(name)){ return Optional.of(mage); }
        }
        return Optional.empty();
    }
    public void delete(String name) throws IllegalArgumentException{
        Optional<Mage> mage = find(name);
        if(mage.isPresent()){ collecion.remove(mage.get()); }
        else { throw new IllegalArgumentException(); }
    }
    public void save(Mage mage){
        Optional<Mage> foundMage = find(mage.getName());
        if (foundMage.isPresent()){ throw new IllegalArgumentException(); }
        else { collecion.add(mage); }

    }
}