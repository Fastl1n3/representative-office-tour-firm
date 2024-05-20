package ru.burym.representativeOfficeTourFirm.services;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.burym.representativeOfficeTourFirm.models.entities.Storage;
import ru.burym.representativeOfficeTourFirm.models.outputs.CargoWithOwnerOutput;
import ru.burym.representativeOfficeTourFirm.models.queries.CargoTypeStat;
import ru.burym.representativeOfficeTourFirm.models.queries.StorageStat;
import ru.burym.representativeOfficeTourFirm.repositories.StorageRepository;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class StorageService {

    private final StorageRepository storageRepository;

    private final TouristService touristService;

    public StorageService(StorageRepository storageRepository, TouristService touristService) {
        this.storageRepository = storageRepository;
        this.touristService = touristService;
    }

    @Transactional(readOnly = true)
    public List<Storage> findAll() {
        return (List<Storage>) storageRepository.findAll();
    }

    @Transactional
    public void save(Storage storage) {
        storageRepository.save(storage);
    }

    @Transactional(readOnly = true)
    public Storage findById(int id) {
        return storageRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Storage not found!"));
    }

    @Transactional(readOnly = true)
    public CargoWithOwnerOutput findCargoWithOwnerById(int cargoId) {
        Storage cargo = storageRepository.findById(cargoId).orElseThrow(() -> new IllegalArgumentException("Cargo not found!"));
        return new CargoWithOwnerOutput(cargo, touristService.findById(cargo.getOwnerId()));
    }

    @Transactional(readOnly = true)
    public List<Storage> findWithoutTransportList() {
        return storageRepository.findWithoutTransportList();
    }

    @Transactional(readOnly = true)
    public List<Storage> findWithoutTransportListByTouristId(int touristId) {
        return storageRepository.findWithoutTransportListByTouristId(touristId);
    }

    @Transactional(readOnly = true)
    public StorageStat getStorageStatByDate(LocalDateTime start_date, LocalDateTime end_date) {
        return storageRepository.getStorageStatByDate(start_date, end_date);
    }

    @Transactional(readOnly = true)
    public List<CargoTypeStat> getCargoTypeStat() {
        return storageRepository.getCargoTypeStat();
    }

}
