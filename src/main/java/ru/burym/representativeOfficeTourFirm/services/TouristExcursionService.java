package ru.burym.representativeOfficeTourFirm.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.burym.representativeOfficeTourFirm.models.entities.TouristExcursion;
import ru.burym.representativeOfficeTourFirm.models.outputs.TouristExcurOutput;
import ru.burym.representativeOfficeTourFirm.repositories.TouristExcursionRepository;
import ru.burym.representativeOfficeTourFirm.repositories.TouristGroupRepository;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class TouristExcursionService {

    private final TouristGroupRepository touristGroupRepository;

    private final TouristExcursionRepository touristExcursionRepository;

    @Autowired
    public TouristExcursionService(TouristGroupRepository touristGroupRepository, TouristExcursionRepository touristExcursionRepository) {
        this.touristGroupRepository = touristGroupRepository;
        this.touristExcursionRepository = touristExcursionRepository;
    }

    @Transactional(readOnly = true)
    public List<TouristExcurOutput> findRelevantTouristsByDate(LocalDateTime dateTime) {
        return touristGroupRepository.findRelevantTouristsForExcursionByDate(dateTime);
    }

    @Transactional
    public void save(TouristExcursion touristExcursion) {
        touristExcursion.setTouristExcursionPK(new TouristExcursion.TouristExcursionPK(touristExcursion.getTouristGroupId(), touristExcursion.getExcursionId()));
        touristExcursionRepository.saveEntity(touristExcursion);
    }

    @Transactional(readOnly = true)
    public List<TouristExcurOutput> findSignedTourists(int excId, LocalDateTime dateTime) {
        return touristExcursionRepository.findTouristsOnExcursion(excId, dateTime);
    }

    @Transactional(readOnly = true)
    public int getCountTouristsByDate(LocalDateTime start_date, LocalDateTime end_date) {
        return touristExcursionRepository.getCountTouristsByDate(start_date, end_date);
    }
}
