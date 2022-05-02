import javax.annotation.processing.SupportedSourceVersion;
import java.util.*;

class main {
    public static void main(String[] args){
        Family_member x = new Family_member();
        Family_member temp;
        Prepare_set ok = new Prepare_set();
        Map <Integer,Integer> map = null;
        System.out.println("Wybierz sortowanie");
        System.out.println("1 - brak sortowania");
        System.out.println("2 - sortowanie zgodnie z naturalnym porzadkiem");
        System.out.println("3 - sortowanie zgodnie z alternatywnym kryterium");
        System.out.println();
        switch (args[0]){
            case "0":
                x = ok.Prepare_set(-1);
                x.children.iterator().next().children.add(new Family_member("Zuzanna",56,156.4,-1));
                temp = x.children.iterator().next().children.iterator().next();
                temp.children.add(new Family_member("Marek",16,130.0,-1));
                map = x.members(x,-1);
                break;
            case "1":
                x = ok.Prepare_set(1);
                x.children.iterator().next().children.add(new Family_member("Zuzanna",56,156.4,1));
                x.children.iterator().next().children.add(new Family_member("Zuzanna",55,140.0,1));
                temp = x.children.iterator().next().children.iterator().next();
                temp.children.add(new Family_member("Marek",16,130.0,1));
                map = x.members(x,1);
                break;
            case "2":
                x = ok.Prepare_set(2);
                x.children.iterator().next().children.add(new Family_member("Zuzanna",56,156.4,2));
                map = x.members(x,2);
                break;
        }
        new Recursively_print(x);
        System.out.println();
        temp = x.children.iterator().next();
        for(Map.Entry m : map.entrySet()){
            Integer it=0;
            for(Family_member mem : x.children){
                if(it == m.getKey()){
                    temp = mem;
                    break;
                }
                it++;
            }
            System.out.println(temp.name+" have "+m.getValue()+" family members");
            temp = x.children.iterator().next();
        }
    }
}

