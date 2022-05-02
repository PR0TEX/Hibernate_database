package entities;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Mage implements Comparable<Mage>{
        @Id
        private String name;
        private int level;


        public Mage(){}
        public Mage(int level, String name){
            this.level = level;
            this.name = name;
        }
        public void setLevel(int level){
            this.level = level;
        }
        public void setName(String name){
            this.name = name;
        }
        public int getLevel(){
            return level;
        }
        public String getName(){
            return name;
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
            hash = 19 * hash + getLevel();
            hash = 19 * hash + (getName() != null ? getName().hashCode() : 0);
            return hash;
        }
        @Override
        public int compareTo(Mage mage){
            int result = this.name.hashCode() - mage.getName().hashCode();
            if(result == 0){ return this.level - mage.getLevel(); }
            return result;
        }
        @Override
        public String toString() {
            return "entities.Mage called " + name + " with level " + level;
        }

    }