package entities;

import entities.Mage;
import lombok.*;

import javax.persistence.*;
import java.util.List;
@Getter
@Setter
@Builder
@Entity
@NoArgsConstructor
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class Tower {
    @Getter
    @Id
    private String name;
    @Getter
    @Setter
    private int height;

    @Getter
    @Setter
    @OneToMany
    private List<Mage> mages;

    @Override public String toString(){
        return "Tower: "+this.name+" height: "+this.height;
    }
    @Override
    public boolean equals(Object v) {
        boolean retVal = false;

        if (v instanceof Mage){
            Mage ptr = (Mage) v;
            retVal = ptr.getName() == getName();
        }

        return retVal;
    }
    @Override
    public int hashCode(){
        int hash =7;
        hash = 19 * hash + getHeight();
        hash = 19 * hash + (getName() != null ? getName().hashCode() : 0);
        return hash;
    }
}
