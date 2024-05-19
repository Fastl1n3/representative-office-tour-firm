package ru.burym.representativeOfficeTourFirm.models.entities;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;

import java.math.BigDecimal;

@Getter
public class HotelRoom {

    @Id
    private Integer id;

    @Setter
    private String hotelName;

    @Setter
    private String typeRoom;

    @Setter
    private BigDecimal dayPrice;

    public HotelRoom(String hotelName, String typeRoom, BigDecimal dayPrice) {
        this.hotelName = hotelName;
        this.typeRoom = typeRoom;
        this.dayPrice = dayPrice;
    }

}