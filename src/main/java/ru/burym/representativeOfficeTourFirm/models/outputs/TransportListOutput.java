package ru.burym.representativeOfficeTourFirm.models.outputs;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import ru.burym.representativeOfficeTourFirm.models.entities.Flight;
import ru.burym.representativeOfficeTourFirm.models.entities.Storage;
import ru.burym.representativeOfficeTourFirm.models.entities.Tourist;
import ru.burym.representativeOfficeTourFirm.models.entities.TransportList;

import java.util.List;


@Getter
@Setter
@AllArgsConstructor
public class TransportListOutput {

    private TransportList transportList;

    private List<Storage> cargoList;

    private Tourist tourist;

    private Flight flight;
}
