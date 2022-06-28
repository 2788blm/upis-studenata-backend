package com.dodeka.upisstudenatabackend.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.UpdateTimestamp;
import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.Set;

@Entity
@Data
public class Anketa {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @ManyToOne
    private Student student;

    @UpdateTimestamp
    private LocalDateTime datum;

    private int godinaStudija;

    private int espb;

    @ManyToMany
    private Set<Predmet> predmeti;

    public Anketa(Student student, int godinaStudija, Set<Predmet> predmeti) {
        this.student = student;
        this.godinaStudija = godinaStudija;
        this.predmeti = predmeti;
        calculateEspb();
    }


    public void calculateEspb() {
        espb = 0;
        if(predmeti != null || !predmeti.isEmpty()) {
            for (Predmet predmet : predmeti) {
                espb += predmet.getEspb();
            }
        }
    }

//    {
//        espb = 0;
//        if(predmeti != null || !predmeti.isEmpty()) {
//            for (Predmet predmet : predmeti) {
//                espb += predmet.getEspb();
//            }
//        }
//    }


}
