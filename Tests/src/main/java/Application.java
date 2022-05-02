import controller.MageController;
import repositories.MageRepository;

public class Application {
    public static void main(String[] args){
        MageRepository mr = new MageRepository();
        MageController mc = new MageController(mr);

        mc.save("John",10);
        mc.save("Greg",12);
        mc.save("Paul",13);
        System.out.println(mc.findAllMages());
        System.out.println("");

        System.out.println("Usunięcie nieistniejącego obiektu");
        System.out.println(mc.delete("Dawid"));

        System.out.println("Znalezienie nieistniejącego obiektu");
        System.out.println(mc.find("Dawid"));

        System.out.println("Dodanie istniejącego obiektu");
        System.out.println(mc.save("Andrzej",1));

        System.out.println("Zapis nowego obiektu");
        System.out.println(mc.save("Dawid",4));

        System.out.println("Znalezienie istniejącego obiektu");
        System.out.println(mc.find("Andrzej"));

        System.out.println("Usunięcie nieistniejącego obiektu");
        System.out.println(mc.delete("Andrzej"));
    }
}
