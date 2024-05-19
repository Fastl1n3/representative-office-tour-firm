package ru.burym.representativeOfficeTourFirm.models.queries;

import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Getter
@Setter
public class ExpenseInfo {

    private LocalDateTime spendDate;

    private String serviceType;

    private BigDecimal expense;

    private BigDecimal income;

    private BigDecimal profit;

}
