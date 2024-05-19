package ru.burym.representativeOfficeTourFirm.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.burym.representativeOfficeTourFirm.models.queries.SpendingReport;
import ru.burym.representativeOfficeTourFirm.repositories.SpendingRepository;

@Service
public class SpendingService {

    private final SpendingRepository spendingRepository;

    @Autowired
    public SpendingService(SpendingRepository spendingRepository) {
        this.spendingRepository = spendingRepository;
    }

    public SpendingReport getGroupReport(int groupId) {

        return spendingRepository.getGroupReport(groupId);
    }
}
