package ru.burym.representativeOfficeTourFirm.models.queries;

import lombok.AllArgsConstructor;
import lombok.Getter;
import ru.burym.representativeOfficeTourFirm.models.entities.Storage;

import java.time.LocalDateTime;
import java.util.List;

@AllArgsConstructor
@Getter
public class TouristAnotherInfo {

    private int numVisits;

    private List<DatePair> arrAndDepDates;

    private List<String> hotels;

    private List<VisistedExcursion> visitedExcursions;

    private List<Storage> cargoList;

    @AllArgsConstructor
    @Getter
    public class DatePair {
        private LocalDateTime dateTo;
        private LocalDateTime dateBack;
    }

    @AllArgsConstructor
    @Getter
    public class VisistedExcursion {
        private int excursionId;
        private String name;
        private LocalDateTime visitDate;
        private String agency;

    }
}
