package ru.burym.representativeOfficeTourFirm.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.burym.representativeOfficeTourFirm.models.entities.TransportList;
import ru.burym.representativeOfficeTourFirm.repositories.StorageRepository;
import ru.burym.representativeOfficeTourFirm.repositories.TransportListRepository;

import java.util.List;

@Service
public class TransportListService {

    private final TransportListRepository transportListRepository;

    private final StorageRepository storageRepository;


    @Autowired
    public TransportListService(TransportListRepository transportListRepository, StorageRepository storageRepository) {
        this.transportListRepository = transportListRepository;
        this.storageRepository = storageRepository;
    }

    @Transactional(readOnly = true)
    public List<TransportList> findAll() {
        return (List<TransportList>) transportListRepository.findAll();
    }

    @Transactional
    public void save(TransportList transportList) {
        transportList.setSeatsNumber(transportList.getCargoList().size());

        List<Integer> cargoList = transportList.getCargoList();
        transportList = transportListRepository.save(transportList);
        for (Integer cargoId : cargoList) {
            storageRepository.setTransportListById(cargoId, transportList.getListId());
        }


    }
}
