package study.p3;

import javax.persistence.Entity;

@Entity
public class Car extends Automobile {

    private int doors;

    public Car() {
    }

    public int getDoors() {
        return doors;
    }

    public void setDoors(int doors) {
        this.doors = doors;
    }
}
