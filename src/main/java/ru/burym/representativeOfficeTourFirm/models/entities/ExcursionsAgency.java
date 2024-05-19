package ru.burym.representativeOfficeTourFirm.models.entities;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;


@Getter
public class ExcursionsAgency {

    @Id
    private int agencyId;

    @Setter
    private String name;

    @Setter
    private double rating;

    public ExcursionsAgency(String name, double rating) {
        this.name = name;
        this.rating = rating;
    }
}
