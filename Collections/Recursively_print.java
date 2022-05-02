public class Recursively_print {

    Recursively_print(Family_member x){
        Family_member temp;
        String line="-";
        System.out.println(line+x);
            for(Family_member next : x.children){
                temp = next;
                line += "-";
                do{
                    System.out.print(line);
                    System.out.println(next);
                    if(next.children.size() <= 1) {
                        next = next.children.size() == 0 ? null : next.children.iterator().next();
                    }else{
                        line+="-";
                        print(next,line);
                        break;
                    }
                    line += "-";
                }while(next != null);
                next = temp;
                line = "-";
            }
    }
    public void print(Family_member x, String line){
        for(Family_member next : x.children){
            System.out.println(line+next);
            if(next.children.size() >= 1){
                print(next,line+"-");
            }
        }
    }
}
