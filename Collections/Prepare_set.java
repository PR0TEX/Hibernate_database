public class Prepare_set {
    Prepare_set(){

    }
     Family_member Prepare_set(int value){
        Family_member x = new Family_member("Jan",80,170.5,value);
        x.children.add(new Family_member("Andrzej",80,176.5,value));
        x.children.add(new Family_member("Zbigniew",80,171.5,value));
        x.children.add(new Family_member("Tomek",19,191.5,value));
        x.children.add(new Family_member("Tomek",19,193.5,value));
        x.children.add(new Family_member("Zosia",25,160.5,value));
        x.children.add(new Family_member("Magda",30,167.0,value));
        x.children.add(new Family_member("Miroslaw",60,160.7,value));
        x.children.add(new Family_member("Magda",15,140.5,value));
        x.children.add(new Family_member("Pawel",22,170.5,value));
        x.children.add(new Family_member("Piotr",13,197.0,value));
        x.children.add(new Family_member("Szymon",17,110.7,value));
        return x;
    }
}
