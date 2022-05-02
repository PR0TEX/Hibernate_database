import java.util.Comparator;

public class Alternative implements Comparator<Family_member> {
    public int compare(Family_member o1, Family_member o2) {
        if(!o1.name.equals(o2.name)){
            return o1.name.compareTo(o2.name);
        }else if(o1.height != o2.height){
            return (int) o1.height - (int) o2.height;
        }else{
            return o1.age - o2.age;
        }
    }
}
