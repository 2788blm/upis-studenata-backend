package com.dodeka.upisstudenatabackend.domain;

import lombok.Builder;
import lombok.Data;
import org.hibernate.annotations.UpdateTimestamp;
import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Data
@Builder
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
    private List<Predmet> predmeti;

    public void setEspb() {
        espb = 0;
        for (Predmet predmet : predmeti) {
            espb += predmet.getEspb();
        }
    }
}
