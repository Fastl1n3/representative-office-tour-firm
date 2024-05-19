package ru.burym.representativeOfficeTourFirm.models.queries;

import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Getter
@Setter
public class SpendingReport {

    private String firstname;

    private String lastname;

    private String patronymic;

    private String serviceType;

    private BigDecimal cost;

    private LocalDateTime spendDate;
}
