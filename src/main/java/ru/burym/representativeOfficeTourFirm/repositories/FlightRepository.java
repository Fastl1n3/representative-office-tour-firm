package ru.burym.representativeOfficeTourFirm.repositories;

import org.springframework.data.jdbc.repository.query.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import ru.burym.representativeOfficeTourFirm.models.entities.Flight;
import ru.burym.representativeOfficeTourFirm.models.queries.CargoInfoByFlight;
import ru.burym.representativeOfficeTourFirm.models.queries.FlightWorkload;
import ru.burym.representativeOfficeTourFirm.models.queries.TouristInfoByFlight;

import java.util.List;
import java.util.Optional;

@Repository
public interface FlightRepository extends CrudRepository<Flight, Integer> {

    @Query("WITH num_seats as (SELECT f.flight_id, SUM(tl.seats_number) all_seats " +
            "FROM Flight f JOIN Transport_list tl on f.flight_id = tl.flight_id " +
            "WHERE f.flight_id=:flightId " +
            "GROUP BY f.flight_id), " +
            "weight as (SELECT SUM(weight) all_weight FROM num_seats, Transport_list tl " +
            "JOIN Storage s on tl.list_id = s.transport_list_id " +
            "WHERE tl.flight_id =num_seats.flight_id) " +
            "SELECT flight_id, all_seats, all_weight FROM num_seats, weight ")
    Optional<FlightWorkload> getFlightWorkload(int flightId);

    @Query("SELECT g.group_id, t.firstname, t.lastname, t.patronymic, t.date_of_birth, hotel_name, type_room FROM Flight " +
            "JOIN \"Group\" g on flight.flight_id = g.flight_to OR flight.flight_id = g.flight_back " +
            "JOIN Tourist_group tg on g.group_id = tg.group_id " +
            "JOIN Tourist t on t.tourist_id = tg.tourist_id " +
            "JOIN Accommodation a on tg.id = a.tourist_group_id " +
            "JOIN hotel_room hr on hr.id = a.hotel_room_id " +
            "WHERE flight_id = :flightId ")
    List<TouristInfoByFlight> getTouristInfoByFlight(int flightId);

    @Query("SELECT s.marking, s.weight, s.package, t.firstname owner_firstname, t.lastname owner_lastname, t.patronymic owner_patronymic FROM Flight f " +
            "JOIN Transport_list tl on f.flight_id = tl.flight_id " +
            "JOIN Storage s on tl.list_id = s.transport_list_id " +
            "JOIN Tourist t on t.tourist_id = s.owner_id " +
            "WHERE flight_id = :flightId")
    List<CargoInfoByFlight> getCargoInfoByFlight(int flightId);

}
