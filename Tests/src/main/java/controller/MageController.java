package controller;

import entities.Mage;
import repositories.MageRepository;

import java.util.List;
import java.util.Optional;

public class MageController{

    private final MageRepository repos;

    public MageController(MageRepository repos){ this.repos = repos; }

    public List<Mage> findAllMages(){ return repos.findAll(); }

    public String find(String name){
        String out = "not found";
        Optional<Mage> mage = repos.find(name);
        if(mage.isPresent()){
            out = mage.get().toString();
        }
        return out;
    }
    public String delete(String name){
        String out = "deleted";
        try{
            this.repos.delete(name);
        }catch (IllegalArgumentException ex){
            out = "entities.Mage not exist";
        }
        return out;
    }
    public String save(String name, int level){
        String out = "saved";
        try{
            this.repos.save(new Mage(level,name));
        }catch (IllegalArgumentException ex){
            out = "entities.Mage already exist";
        }
        return out;
    }
}