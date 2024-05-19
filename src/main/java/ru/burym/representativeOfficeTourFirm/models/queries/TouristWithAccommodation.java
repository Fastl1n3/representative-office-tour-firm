package ru.burym.representativeOfficeTourFirm.models.queries;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import ru.burym.representativeOfficeTourFirm.models.entities.Accommodation;
import ru.burym.representativeOfficeTourFirm.models.entities.HotelRoom;
import ru.burym.representativeOfficeTourFirm.models.entities.Tourist;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
public class TouristWithAccommodation {
    @Setter
    private String firstname;

    @Setter
    private String lastname;

    @Setter
    private String patronymic;

    @Setter
    private long passportId;

    @Setter
    private LocalDate dateOfBirth;

    @Setter
    private LocalDateTime checkInDate;

    @Setter
    private LocalDateTime checkOutDate;

    @Setter
    private String hotelName;

    @Setter
    private String typeRoom;

}
