import java.util.*;

public class Family_member implements Comparable <Family_member>{
    public String name;
    public int age;
    public double height;
     public Set<Family_member> children;

    Family_member(){
        name=" ";
        age=0;
        height = 0.0;
        children = null;
    }
    Family_member(String name, int age, double height,int flag){
        this.name = name;
        this.age = age;
        this.height = height;

        if(flag == 1){
            this.children = new TreeSet<Family_member>();
        }else if(flag == 2){
            this.children = new TreeSet<Family_member>(new Alternative());
        }else{
            this.children = new HashSet<Family_member>();
        }
    }

    @Override
    public boolean equals(Object mem){
        if(mem == null){
            return false;
        }
        if(getClass() != mem.getClass()){
            return false;
        }
        final Family_member other = (Family_member) mem;
        if(this.name.equals(other.name)){
            if(this.age == other.age){
                if(this.height == other.height){
                    return true;
                }
            }
        }
        return false;
    }
    @Override
    public int hashCode(){
        int hash =7;
        hash = 19 * hash + this.age;
        hash = 19 * hash + (this.name != null ? this.name.hashCode() : 0);
        hash = 19 * hash + (int) this.height;
        return hash;
    }
    @Override
    public int compareTo(Family_member mem){
        if(this.age != mem.age){
            return this.age - mem.age;
        }else if(!this.name.equals(mem.name)){
            return this.name.compareTo(mem.name);
        }else{
            return (int) this.height - (int) mem.height;
        }
    }
    public static final Comparator<Family_member> altComparator = new Comparator<Family_member>() {
        @Override
        public int compare(Family_member o1, Family_member o2) {
           if(!o1.name.equals(o2.name)){
               return o1.name.compareTo(o2.name);
           }else if(o1.height != o2.height){
               return (int) o1.height - (int) o2.height;
           }else{
               return o1.age - o2.age;
           }
        }
    };
    public Map<Integer,Integer> members(Family_member x, int flag){
        int counter=0;
        int index=-1;
        Map<Integer, Integer> numOfMem;
        numOfMem = flag == -1 ? new HashMap<Integer, Integer>() :
                 new TreeMap<Integer,Integer>();
        Family_member temp;
        for(Family_member next : x.children){
            counter = 0;
            index++;
            temp = next;
            do{
                if(next.children.size() <= 1) {
                    next = next.children.size() == 0 ? null : next.children.iterator().next();
                    counter++;
                }else{
                    counter += deepSearch(next);
                    break;
                }

            }while(next != null);
            next = temp;
            numOfMem.put(index,counter);
        }
        return numOfMem;
    }
    public int deepSearch(Family_member x){
        int counter=0;
        for(Family_member next : x.children){
            counter++;
            if(next.children.size() >= 1){
                counter += deepSearch(next);
            }
        }
        return counter;
    }
    public String toString(){
        return getClass().getName().toString()+"{name="+this.name+", age="+
                this.age+", height="+this.height+"}";
    }
    public void recur_print(Family_member mem){
        System.out.print(mem);
    }

}
