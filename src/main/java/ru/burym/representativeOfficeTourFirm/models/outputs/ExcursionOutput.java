package ru.burym.representativeOfficeTourFirm.models.outputs;

import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Getter

public class ExcursionOutput {

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

    @Setter
    private String agencyName;

    @Setter
    private double rating;

    //private List<LocalDateTime>

}
