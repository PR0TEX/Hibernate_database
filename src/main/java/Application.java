import entities.Mage;
import entities.Tower;
import repositories.MageRepository;
import repositories.TowerRepository;

import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.util.List;

public class Application {
    public static void main(String[] args){
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("187586");
        MageRepository magRep = new MageRepository(emf);
        TowerRepository towRep = new TowerRepository(emf);

        init(magRep, towRep);
        System.out.println("All Mages and towers after query");

        printMage(magRep.findAll());
        printTowers(towRep.findAll());
    }

    public static void init(MageRepository magRep, TowerRepository towRep){
        Tower tow1 = Tower.builder()
                .name("Tower1")
                .height(15)
                .build();
        Mage mag1 = Mage.builder()
                .name("Mage1")
                .level(10)
                .build();
        Mage mag2 = Mage.builder()
                .name("Mage2")
                .level(100)
                .build();
        Mage mag3 = Mage.builder()
                .name("Mage3")
                .level(5)
                .build();

        Mage mag4 = Mage.builder()
                .name("Mage4")
                .level(15)
                .build();

        towRep.add(tow1);

        mag1.setTower(tow1);
        mag2.setTower(tow1);
        mag3.setTower(null);
        mag4.setTower(tow1);

        magRep.add(mag1);
        magRep.add(mag2);
        magRep.add(mag3);
        magRep.add(mag4);

        System.out.println("Mages before query");
        printMage(magRep.findAll());

//        List<Mage> mages = magRep.findAllGreaterThan(11);
//        List<Mage> magesTower = magRep.findAllGreaterThan(50,tow1);
        List<Mage> levelAboveMin  = magRep.findaGreaterThanMin();
        //        List<Tower> towers = towRep.findAllLowerThan(5);
        for(Mage mage : magRep.findAll()){
            if(!levelAboveMin.contains(mage)){
                magRep.delete(mage);
            }
        }
    }
    public static void printMage(List<Mage> list){
        for(Mage mage: list ){
            System.out.println(mage);
        }
    }
    public static void printTowers(List<Tower> list){
        for(Tower tower: list ){
            System.out.println(tower);
        }
    }
}

