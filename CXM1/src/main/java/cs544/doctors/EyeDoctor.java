package cs544.doctors;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@DiscriminatorValue("Eye Doctor")
public class EyeDoctor extends Doctor {
    public EyeDoctor() {
    }


}
