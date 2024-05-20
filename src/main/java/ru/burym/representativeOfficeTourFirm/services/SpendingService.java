package ru.burym.representativeOfficeTourFirm.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.burym.representativeOfficeTourFirm.models.queries.ExpenseInfo;
import ru.burym.representativeOfficeTourFirm.models.queries.ProfitabilityInfo;
import ru.burym.representativeOfficeTourFirm.models.queries.SpendingReport;
import ru.burym.representativeOfficeTourFirm.repositories.SpendingRepository;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class SpendingService {

    private final SpendingRepository spendingRepository;

    @Autowired
    public SpendingService(SpendingRepository spendingRepository) {
        this.spendingRepository = spendingRepository;
    }

    @Transactional(readOnly = true)
    public List<SpendingReport> getGroupReport(int groupId) {
        return spendingRepository.getGroupReport(groupId);
    }

    @Transactional(readOnly = true)
    public List<SpendingReport> getGroupReportByCategory(int groupId, String category) {
        return spendingRepository.getGroupReportByCategory(groupId, category);
    }

    @Transactional(readOnly = true)
    public List<ExpenseInfo> getExpAndIncByDate(LocalDateTime startDate, LocalDateTime endDate) {
        return spendingRepository.getExpAndIncByDate(startDate, endDate);
    }

    @Transactional(readOnly = true)
    public ProfitabilityInfo getProfitabilityInfo() {
        return spendingRepository.getProfitabilityInfo();
    }
}
