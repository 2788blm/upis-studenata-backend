package com.dodeka.upisstudenatabackend.domain;

import lombok.Builder;
import lombok.Data;
import org.hibernate.annotations.UpdateTimestamp;
import javax.persistence.Entity;
import javax.persistence.Id;
import java.math.BigDecimal;
import java.time.LocalDate;

@Entity
@Data
@Builder
public class KursnaLista {

    @Id
    @UpdateTimestamp
    private LocalDate datum;

    private BigDecimal srednjiKursDinaraPremaEvru;

}
