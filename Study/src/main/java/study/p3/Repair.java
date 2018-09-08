package study.p3;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
public class Repair {

    @Id
    private int id;

    @Temporal(TemporalType.DATE)
    private Date date;


    private String description;

    @ManyToOne
    private Mechanic mechanic;

    @ManyToOne
    @JoinColumn(name = "automobile_id")
    private Automobile automobile;

    @OneToMany
    @JoinColumn(name = "repair_id")
    private List<Part> parts = new ArrayList<>();

    public Repair() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Mechanic getMechanic() {
        return mechanic;
    }

    public void setMechanic(Mechanic mechanic) {
        this.mechanic = mechanic;
    }

    public Automobile getAutomobile() {
        return automobile;
    }

    public void setAutomobile(Automobile automobile) {
        this.automobile = automobile;
    }

    public List<Part> getParts() {
        return parts;
    }

    public void setParts(List<Part> parts) {
        this.parts = parts;
    }
}
