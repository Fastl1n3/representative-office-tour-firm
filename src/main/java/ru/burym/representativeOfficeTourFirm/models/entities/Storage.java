package ru.burym.representativeOfficeTourFirm.models.entities;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Getter
@NoArgsConstructor
public class Storage {
    @Id
    private Integer cargoId;

    @Setter
    private Integer ownerId;

    @Setter
    private String marking;

    @Setter
    private BigDecimal weight;

    @Setter
    @Column("package")
    private String packageType;

    @Setter
    private LocalDateTime deliveryDate;

    @Setter
    private BigDecimal price;

    @Setter
    private Integer transportListId;


    public Storage(int ownerId, String marking, BigDecimal weight, String packageType, LocalDateTime deliveryDate, BigDecimal price) {
        this.ownerId = ownerId;
        this.marking = marking;
        this.weight = weight;
        this.packageType = packageType;
        this.deliveryDate = deliveryDate;
        this.price = price;
    }

}
