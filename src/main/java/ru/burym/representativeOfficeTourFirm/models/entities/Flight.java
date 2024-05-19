package ru.burym.representativeOfficeTourFirm.models.entities;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.Id;

import java.time.LocalDateTime;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class Flight {

    @Id
    private int flightId;

    @Setter
    private String flightNumber;

    @Setter
    private String planeType;

    @Setter
    private LocalDateTime dateTime;

    @Setter
    private String departCity;

    @Setter
    private String destinCity;

    public Flight(String flightNumber, String planeType, LocalDateTime dateTime, String departCity, String destinCity) {
        this.flightNumber = flightNumber;
        this.planeType = planeType;
        this.dateTime = dateTime;
        this.departCity = departCity;
        this.destinCity = destinCity;
    }
}
