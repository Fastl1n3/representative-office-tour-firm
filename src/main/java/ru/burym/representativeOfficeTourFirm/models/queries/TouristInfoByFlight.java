package ru.burym.representativeOfficeTourFirm.models.queries;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
public class TouristInfoByFlight {

    private int groupId;

    private String firstname;

    private String lastname;

    private String patronymic;

    private LocalDate dateOfBirth;

    private String hotelName;

    private String typeRoom;
}
