package ru.burym.representativeOfficeTourFirm.models.entities;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Getter
@NoArgsConstructor
public class TransportList {
    @Id
    private Integer listId;

    @Setter
    private Integer ownerId;

    @Setter
    private Integer seatsNumber;

    @Setter
    private BigDecimal packagePrice;

    @Setter
    private BigDecimal insurancePrice;

    @Setter
    private BigDecimal deliveryPrice;

    @Setter
    private LocalDateTime compilationDate;

    @Setter
    private Integer flightId;

    @Transient
    @Setter
    List<Integer> cargoList;

    public TransportList(Integer seatsNumber, BigDecimal packagePrice, BigDecimal insurancePrice, BigDecimal deliveryPrice, LocalDateTime compilationDate, Integer flightId) {
        this.seatsNumber = seatsNumber;
        this.packagePrice = packagePrice;
        this.insurancePrice = insurancePrice;
        this.deliveryPrice = deliveryPrice;
        this.compilationDate = compilationDate;
        this.flightId = flightId;
    }
}
