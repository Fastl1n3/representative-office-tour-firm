package ru.burym.representativeOfficeTourFirm.models.entities;


import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.MappedCollection;

import java.time.LocalDate;

@Getter
@NoArgsConstructor
public class Tourist {

    @Id
    private Integer touristId;

    @Setter
    private String firstname;

    @Setter
    private String lastname;

    @Setter
    private String patronymic;

    @Setter
    private long passportId;

    @Setter
    private String gender;

    @Setter
    private LocalDate dateOfBirth;

    @Setter
    private boolean isChild;

    @Setter
    private Integer parent;

    public Tourist(String firstname, String lastname, String patronymic, long passportId, String gender, LocalDate dateOfBirth, boolean isChild, int parent) {
        this.firstname = firstname;
        this.lastname = lastname;
        this.patronymic = patronymic;
        this.passportId = passportId;
        this.gender = gender;
        this.dateOfBirth = dateOfBirth;
        this.isChild = isChild;
        this.parent = parent;
    }
}
