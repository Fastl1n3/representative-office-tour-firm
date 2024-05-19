package ru.burym.representativeOfficeTourFirm.models.entities;


import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.Id;

import java.math.BigDecimal;

@Getter
@NoArgsConstructor
public class Excursion {

    @Id
    @Setter
    private Integer excursionId;

    @Setter
    private String name;

    @Setter
    private String description;

    @Setter
    private BigDecimal price;

    @Setter
    private Integer duration;

    @Setter
    private Integer agencyId;

    public Excursion(String name, String description, BigDecimal price, int duration, int agencyId) {
        this.name = name;
        this.description = description;
        this.price = price;
        this.duration = duration;
        this.agencyId = agencyId;
    }
}
