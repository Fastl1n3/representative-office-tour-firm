package ru.burym.representativeOfficeTourFirm.models.queries;

import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
public class StorageStat {

    private int numSeats;

    private BigDecimal weight;

    private int cargoPlanes;

    private int cargoPassPlanes;

    private int allPlanes;
}
