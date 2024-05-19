package ru.burym.representativeOfficeTourFirm.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.burym.representativeOfficeTourFirm.models.entities.Accommodation;
import ru.burym.representativeOfficeTourFirm.models.queries.TouristWithAccommodation;
import ru.burym.representativeOfficeTourFirm.repositories.AccommodationRepository;

import java.util.List;

@Service
public class AccommodationService {

    private final AccommodationRepository accommodationRepository;

    @Autowired
    public AccommodationService(AccommodationRepository accommodationRepository) {
        this.accommodationRepository = accommodationRepository;
    }

    @Transactional
    public void save(Accommodation accommodation) {
        accommodationRepository.save(accommodation);
    }

    @Transactional(readOnly = true)
    public List<TouristWithAccommodation> getTouristAccommodationByGroupId(int groupId) {
        return accommodationRepository.getTouristAccommodationByGroupId(groupId);
    }
}
