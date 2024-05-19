package ru.burym.representativeOfficeTourFirm.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.burym.representativeOfficeTourFirm.models.entities.Tourist;
import ru.burym.representativeOfficeTourFirm.models.queries.TouristAnotherInfo;
import ru.burym.representativeOfficeTourFirm.repositories.StorageRepository;
import ru.burym.representativeOfficeTourFirm.repositories.TouristRepository;

import java.util.List;

@Service
public class TouristService {

    private final TouristRepository touristRepository;
    private final StorageRepository storageRepository;

    @Autowired
    public TouristService(TouristRepository touristRepository, StorageRepository storageRepository) {
        this.touristRepository = touristRepository;
        this.storageRepository = storageRepository;
    }

    @Transactional(readOnly = true)
    public List<Tourist> findAll() {
        return (List<Tourist>) touristRepository.findAll();
    }

    @Transactional(readOnly = true)
    public Tourist findById(int id) {
        return touristRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Tourist not found!"));
    }

    @Transactional(readOnly = true)
    public TouristAnotherInfo getTouristAnotherInfo(int id) {
        return new TouristAnotherInfo(touristRepository.countVisitsByTouristId(id), touristRepository.getArrAndDepDatesByTouristId(id),
                touristRepository.getHotelsByTouristId(id), touristRepository.getVisitedExcursionsByTouristId(id), storageRepository.findByOwnerId(id));
    }

    @Transactional(readOnly = true)
    public List<Tourist> findAllAdult() {
        return touristRepository.findAllAdult();
    }
}
