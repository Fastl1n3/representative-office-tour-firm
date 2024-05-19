package ru.burym.representativeOfficeTourFirm.models.queries;

import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Service;

@Getter
@Setter
public class MostPopularExcursion {

    private int excursionId;

    private String name;

    private String agency;
}
