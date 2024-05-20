package ru.burym.representativeOfficeTourFirm.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.burym.representativeOfficeTourFirm.models.entities.Excursion;
import ru.burym.representativeOfficeTourFirm.models.entities.ExcursionSchedule;
import ru.burym.representativeOfficeTourFirm.models.entities.ExcursionsAgency;
import ru.burym.representativeOfficeTourFirm.models.outputs.ExcursionOutput;
import ru.burym.representativeOfficeTourFirm.models.outputs.ExcursionWithScheduleOutput;
import ru.burym.representativeOfficeTourFirm.models.queries.MostPopularExcursion;
import ru.burym.representativeOfficeTourFirm.repositories.ExcursionRepository;
import ru.burym.representativeOfficeTourFirm.repositories.ExcursionsAgencyRepository;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class ExcursionService {

    private final ExcursionRepository excursionRepository;

    private final ExcursionsAgencyRepository excursionsAgencyRepository;

    @Autowired
    public ExcursionService(ExcursionRepository excursionRepository, ExcursionsAgencyRepository excursionsAgencyRepository) {
        this.excursionRepository = excursionRepository;
        this.excursionsAgencyRepository = excursionsAgencyRepository;
    }

    @Transactional(readOnly = true)
    public List<ExcursionOutput> findAllWithAgencies() {
        return  excursionRepository.findAllWithAgencies();
    }

    @Transactional(readOnly = true)
    public ExcursionOutput findWithAgencyById(int id) {
        return excursionRepository.findWithAgencyById(id).orElseThrow(() -> new RuntimeException("Excursion not found"));
    }

    @Transactional
    public void save(Excursion excursion) {
        excursionRepository.save(excursion);
    }

    @Transactional(readOnly = true)
    public ExcursionWithScheduleOutput findWithScheduleById(int id) {
        ExcursionOutput excursionInfo = excursionRepository.findWithAgencyById(id).orElseThrow(() -> new RuntimeException("Excursion not found"));
        List<ExcursionSchedule> scheduleList = excursionRepository.findScheduleByExcursionId(id); //TODO filter by current date

        return new ExcursionWithScheduleOutput(excursionInfo, scheduleList);
    }

    @Transactional(readOnly = true)
    public List<ExcursionsAgency> findAllAgencies() {
        return (List<ExcursionsAgency>) excursionsAgencyRepository.findAll();
    }

    @Transactional
    public void saveSchedule(ExcursionSchedule schedule) {
        schedule.setExcursionSchedulePK(new ExcursionSchedule.ExcursionSchedulePK(schedule.getExcursionId(), schedule.getDateTime()));
        excursionRepository.saveSchedule(schedule);
    }

    @Transactional
    public void deleteSchedule(int id, LocalDateTime dateTime) {
        excursionRepository.deleteSchedule(id, dateTime);
    }

    @Transactional
    public void update(Excursion excursion) {
        excursionRepository.save(excursion);
    }

    @Transactional
    public void delete(int id) {
        excursionRepository.deleteById(id);
    }

    @Transactional(readOnly = true)
    public List<MostPopularExcursion> getMostPopular() {
        return excursionRepository.getMostPopular();
    }
    @Transactional(readOnly = true)
    public List<ExcursionsAgency> getBestAgency() {
        return excursionsAgencyRepository.getBestAgency();
    }


}
