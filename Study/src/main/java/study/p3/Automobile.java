package study.p3;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Inheritance(strategy = InheritanceType.JOINED)
public class Automobile {

    @Id
    @GeneratedValue
    private int id;

    private String color;
    private String make;
    private String model;
    private int year;

    @OneToMany(mappedBy = "automobile")
    private List<Repair> repairs = new ArrayList<>();

}
