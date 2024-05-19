package ru.burym.representativeOfficeTourFirm.models.entities;

import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.MappedCollection;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Getter
@NoArgsConstructor
public class Spending {
    @Id
    private Long id;


    private int touristId;


    private String serviceType;


    private BigDecimal cost;


    private LocalDateTime spendDate;

    public Spending(int touristId, String serviceType, BigDecimal cost, LocalDateTime spendDate) {
        this.touristId = touristId;
        this.serviceType = serviceType;
        this.cost = cost;
        this.spendDate = spendDate;
    }
}
