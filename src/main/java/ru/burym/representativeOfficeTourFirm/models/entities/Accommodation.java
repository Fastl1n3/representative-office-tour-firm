package ru.burym.representativeOfficeTourFirm.models.entities;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.Id;

import java.time.LocalDateTime;

@Getter
@NoArgsConstructor
public class Accommodation {

    @Id
    @Setter
    private Integer touristGroupId;

    @Setter
    private LocalDateTime checkInDate;

    @Setter
    private LocalDateTime checkOutDate;

    @Setter
    private Integer hotelRoomId;

    public Accommodation(int touristGroupId, LocalDateTime checkInDate, LocalDateTime checkOutDate, int hotelRoomId) {
        this.touristGroupId = touristGroupId;
        this.checkInDate = checkInDate;
        this.checkOutDate = checkOutDate;
        this.hotelRoomId = hotelRoomId;
    }
}
