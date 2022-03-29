package entities;

import lombok.*;
import javax.persistence.*;

@Entity
@Getter
@Builder
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class Mage{

    @Id
    @Getter
    private String name;

    @Getter
    @Setter
    private int level;

    @ManyToOne
    private Tower tower;

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
        hash = 19 * hash + getLevel();
        hash = 19 * hash + (getName() != null ? getName().hashCode() : 0);
        return hash;
    }

}
