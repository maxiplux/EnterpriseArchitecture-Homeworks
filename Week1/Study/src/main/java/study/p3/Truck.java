package study.p3;

import javax.persistence.Entity;

@Entity
public class Truck extends Automobile {

    private int caryCapacity;

    public Truck() {
    }

    public int getCaryCapacity() {
        return caryCapacity;
    }

    public void setCaryCapacity(int caryCapacity) {
        this.caryCapacity = caryCapacity;
    }
}
