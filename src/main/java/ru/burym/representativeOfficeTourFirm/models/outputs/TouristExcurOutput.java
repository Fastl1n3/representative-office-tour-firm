package ru.burym.representativeOfficeTourFirm.models.outputs;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@NoArgsConstructor
public class TouristExcurOutput {
    @Setter
    private Integer touristGroupId;

    @Setter
    private String touristType;

    @Setter
    private String firstname;

    @Setter
    private String lastname;

    @Setter
    private String patronymic;

    @Setter
    private long passportId;
}
