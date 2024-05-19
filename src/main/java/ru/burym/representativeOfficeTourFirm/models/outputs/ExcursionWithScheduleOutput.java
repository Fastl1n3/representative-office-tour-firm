package ru.burym.representativeOfficeTourFirm.models.outputs;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import ru.burym.representativeOfficeTourFirm.models.entities.ExcursionSchedule;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
public class ExcursionWithScheduleOutput {

    private ExcursionOutput excursionInfo;

    private List<ExcursionSchedule> scheduleList;

}
