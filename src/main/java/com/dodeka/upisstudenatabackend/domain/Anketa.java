package com.dodeka.upisstudenatabackend.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Anketa {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int anketaId;

    @UpdateTimestamp
    private LocalDateTime datumPopunjavanjaAnkete;

    private String imeStudenta;

    private String prezimeStudenta;

    private String brIndeksa; // smer, broj, godina upisa; ne znam kako tacno ovo treba da izgleda

    private GodinaStudija godinaStudija;

    private boolean prviPutUpisujeGodinu;

    private int brUpisanihESPB; // posto se ovo ne unosi nego generise na osnovu liste predmeta da li je potrebno da se nekako exclude-uju iz onih anotacija gore o konstruktoru i builderu

    @ManyToMany(mappedBy = "listaPredmeta", cascade = CascadeType.ALL)
    private List<Predmet> listaPredmeta;

    @ManyToOne
    private String email;


    private void setBrUpisanihESPB() {
        for(Predmet predmet : listaPredmeta) {
            brUpisanihESPB += predmet.getBrESPB();
        }
    }

    public enum GodinaStudija {
        PRVA, DRUGA, TRECA, CETVRTA
    }


}
