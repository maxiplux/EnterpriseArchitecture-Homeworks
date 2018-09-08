package study.p3;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import java.util.ArrayList;
import java.util.List;

@Entity(name = "Technician")
public class Mechanic {

    @EmbeddedId
    private MechanicId id;

    private double salary;
    private String phonenr;

    @OneToMany(mappedBy = "mechanic")
    private List<Repair> repairs = new ArrayList<>();


    public Mechanic() {
    }

    public MechanicId getId() {
        return id;
    }

    public void setId(MechanicId id) {
        this.id = id;
    }

    public double getSalary() {
        return salary;
    }

    public void setSalary(double salary) {
        this.salary = salary;
    }

    public String getPhonenr() {
        return phonenr;
    }

    public void setPhonenr(String phonenr) {
        this.phonenr = phonenr;
    }

    public List<Repair> getRepairs() {
        return repairs;
    }

    public void setRepairs(List<Repair> repairs) {
        this.repairs = repairs;
    }
}
