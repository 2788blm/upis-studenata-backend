package com.dodeka.upisstudenatabackend.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.UpdateTimestamp;
import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.Set;

@Data
@NoArgsConstructor
@Table(name = "anketa")
@Entity
public class Anketa {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Enumerated(EnumType.STRING)
    @Column(name = "godina_studija")
    private GodinaStudija godinaStudijaKojuUpisuje;

    @Enumerated(EnumType.STRING)
    @Column(name = "godina_upisa")
    private TipUpisa tipUpisa;

    private int espb;

    @UpdateTimestamp    // Da li ovo ili nesto drugo posto je time a ne date ???
    private LocalDateTime datumPopunjavanja;

    @ManyToMany(mappedBy = "ankete")
    @JsonIgnore
    private Set<Predmet> predmeti;

    @ManyToOne
    private Student student;



    public Anketa(Student student, GodinaStudija godinaStudija, TipUpisa tipUpisa, Set<Predmet> predmeti) {
        this.student = student;
        this.godinaStudijaKojuUpisuje = godinaStudija;
        this.tipUpisa = tipUpisa;
        this.predmeti = predmeti;
        calculateEspb();
    }

    public void calculateEspb() {
        espb = 0;
        if(predmeti != null && !predmeti.isEmpty()) {
            for (Predmet predmet : predmeti) {
                espb += predmet.getEspb();
            }
        }
    }

//    {
//        espb = 0;
//        if(predmeti != null && !predmeti.isEmpty()) {
//            for (Predmet predmet : predmeti) {
//                espb += predmet.getEspb();
//            }
//        }
//    }


}
