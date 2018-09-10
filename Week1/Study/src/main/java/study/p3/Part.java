package study.p3;

import javax.persistence.*;

@Entity
@SecondaryTable(name = "Accounting")
public class Part {

    @Id
    @GeneratedValue
    private int id;

    private String description;

    @Column(table = "Accounting")
    private double purchasePrice;

    public Part() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public double getPurchasePrice() {
        return purchasePrice;
    }

    public void setPurchasePrice(double purchasePrice) {
        this.purchasePrice = purchasePrice;
    }
}
