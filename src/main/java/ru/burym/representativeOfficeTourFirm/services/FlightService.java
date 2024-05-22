package ru.burym.representativeOfficeTourFirm.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.burym.representativeOfficeTourFirm.models.entities.Flight;
import ru.burym.representativeOfficeTourFirm.models.queries.CargoInfoByFlight;
import ru.burym.representativeOfficeTourFirm.models.queries.FlightWorkload;
import ru.burym.representativeOfficeTourFirm.models.queries.TouristInfoByFlight;
import ru.burym.representativeOfficeTourFirm.repositories.FlightRepository;

import java.util.List;
import java.util.Optional;

@Service
public class FlightService {

    private final FlightRepository flightRepository;

    @Autowired
    public FlightService(FlightRepository flightRepository) {
        this.flightRepository = flightRepository;
    }

    @Transactional(readOnly = true)
    public List<Flight> findAll() {
        return (List<Flight>) flightRepository.findAll();
    }

    @Transactional(readOnly = true)
    public List<Flight> findAllPassengersTo() {
        return flightRepository.findAllPassengersTo();
    }

    @Transactional(readOnly = true)
    public List<Flight> findAllPassengersBack() {
        return flightRepository.findAllPassengersBack();
    }

    @Transactional(readOnly = true)
    public List<Flight> findAllBack() {
        return flightRepository.findAllBack();
    }

    @Transactional(readOnly = true)
    public Flight findById(int id) {
        return flightRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Flight not found"));
    }

    @Transactional(readOnly = true)
    public Optional<FlightWorkload> getFlightWorkload(int flightId) {
        return flightRepository.getFlightWorkload(flightId);
    }

    @Transactional(readOnly = true)
    public List<TouristInfoByFlight> getTouristInfoByFlightId(int flightId) {
        return flightRepository.getTouristInfoByFlightId(flightId);
    }

    @Transactional(readOnly = true)
    public List<CargoInfoByFlight> getCargoInfoByFlight(int flightId) {
        return flightRepository.getCargoInfoByFlight(flightId);
    }



}
