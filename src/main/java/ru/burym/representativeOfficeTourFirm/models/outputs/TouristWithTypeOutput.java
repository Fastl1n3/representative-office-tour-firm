package ru.burym.representativeOfficeTourFirm.models.outputs;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@AllArgsConstructor
@Setter
@NoArgsConstructor
public class TouristWithTypeOutput {

    private Integer touristId;

    private String firstname;

    private String lastname;

    private String patronymic;

    private long passportId;

    private String gender;

    private LocalDate dateOfBirth;

    private String touristType;
}
