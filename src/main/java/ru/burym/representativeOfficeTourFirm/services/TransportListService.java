package ru.burym.representativeOfficeTourFirm.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.burym.representativeOfficeTourFirm.models.entities.Flight;
import ru.burym.representativeOfficeTourFirm.models.entities.Storage;
import ru.burym.representativeOfficeTourFirm.models.entities.Tourist;
import ru.burym.representativeOfficeTourFirm.models.entities.TransportList;
import ru.burym.representativeOfficeTourFirm.models.outputs.TransportListOutput;
import ru.burym.representativeOfficeTourFirm.repositories.StorageRepository;
import ru.burym.representativeOfficeTourFirm.repositories.TransportListRepository;

import java.util.List;

@Service
public class TransportListService {

    private final TransportListRepository transportListRepository;

    private final StorageRepository storageRepository;

    private final TouristService touristService;

    private final FlightService flightService;


    @Autowired
    public TransportListService(TransportListRepository transportListRepository, StorageRepository storageRepository, TouristService touristService, FlightService flightService) {
        this.transportListRepository = transportListRepository;
        this.storageRepository = storageRepository;
        this.touristService = touristService;
        this.flightService = flightService;
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

    @Transactional(readOnly = true)
    public TransportListOutput findWithCargoById(int listId) {
        TransportList transportList = transportListRepository.findById(listId).orElseThrow(() -> new RuntimeException("Transport list not found"));
        List<Storage> cargoList = storageRepository.findByTransportListId(listId);
        Tourist tourist = touristService.findById(transportList.getOwnerId());
        Flight flight = flightService.findById(transportList.getFlightId());

        return new TransportListOutput(transportList, cargoList, tourist, flight);
    }
}
