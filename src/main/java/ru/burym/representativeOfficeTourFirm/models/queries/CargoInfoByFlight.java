package ru.burym.representativeOfficeTourFirm.models.queries;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.relational.core.mapping.Column;

import java.math.BigDecimal;

@Getter
@Setter
public class CargoInfoByFlight {

    private String marking;

    private BigDecimal weight;

    @Column("package")
    private String packageType;

    private String ownerFirstname;

    private String ownerLastname;

    private String ownerPatronymic;
}
