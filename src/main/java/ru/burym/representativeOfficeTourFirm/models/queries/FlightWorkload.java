package ru.burym.representativeOfficeTourFirm.models.queries;

import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
public class FlightWorkload {

    private int flightId;

    private int allSeats;

    private BigDecimal allWeight;
}
