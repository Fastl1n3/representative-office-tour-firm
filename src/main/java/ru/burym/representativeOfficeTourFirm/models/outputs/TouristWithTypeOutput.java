package ru.burym.representativeOfficeTourFirm.models.outputs;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import ru.burym.representativeOfficeTourFirm.models.entities.Tourist;

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

    private LocalDate dateOfBirth;

    private String type;
}
