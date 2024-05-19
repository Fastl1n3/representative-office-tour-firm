package ru.burym.representativeOfficeTourFirm.repositories;

import org.springframework.data.jdbc.repository.query.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import ru.burym.representativeOfficeTourFirm.models.entities.Flight;
import ru.burym.representativeOfficeTourFirm.models.entities.Group;
import ru.burym.representativeOfficeTourFirm.models.entities.Tourist;

import java.util.List;

@Repository
public interface GroupRepository extends CrudRepository<Group, Integer> {

    @Query("SELECT flight_id, flight_number, plane_type, date_time, depart_city, destin_city " +
            "FROM flight JOIN \"Group\" G on flight.flight_id = G.flight_to or flight.flight_id = G.flight_back " +
            "WHERE group_id = :id ORDER BY date_time")
    List<Flight> findFlightsByGroupId(int id);

    @Query("SELECT t.firstname, t.lastname, t.patronymic, t.gender, t.date_of_birth, t.passport_id " +
            "FROM Tourist t JOIN Tourist_Group tg ON t.tourist_id = tg.tourist_id " +
            "WHERE tg.group_id = groupId")
    List<Tourist> getTouristInfoByGroupId(int groupId);

    @Query("SELECT t.firstname, t.lastname, t.patronymic, t.gender, t.date_of_birth, t.passport_id " +
            "FROM Tourist t JOIN Tourist_Group tg ON t.tourist_id = tg.tourist_id " +
            "WHERE tg.group_id = :groupId AND tg.tourist_type = :touristType")
    List<Tourist> getTouristInfoByGroupIdAndType(int groupId, String touristType);
}
