package ru.burym.representativeOfficeTourFirm.models.outputs;

import lombok.AllArgsConstructor;
import lombok.Getter;
import ru.burym.representativeOfficeTourFirm.models.entities.Storage;
import ru.burym.representativeOfficeTourFirm.models.entities.Tourist;

@Getter
@AllArgsConstructor
public class CargoWithOwnerOutput {

    private Storage cargo;
    

    private Tourist tourist;

}
