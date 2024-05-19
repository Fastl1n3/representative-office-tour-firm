package ru.burym.representativeOfficeTourFirm.models.outputs;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.relational.core.mapping.Column;

@Getter
@NoArgsConstructor
public class TouristAccomOutput {

    @Setter
    private Integer touristGroupId;

    @Setter
    private String firstname;

    @Setter
    private String lastname;

    @Setter
    private String patronymic;

    @Setter
    private long passportId;
}
