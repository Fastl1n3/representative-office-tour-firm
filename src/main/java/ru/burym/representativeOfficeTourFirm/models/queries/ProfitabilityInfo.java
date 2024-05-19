package ru.burym.representativeOfficeTourFirm.models.queries;

import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
public class ProfitabilityInfo {

    private BigDecimal expense;

    private BigDecimal income;

    private double efficiency;
}
