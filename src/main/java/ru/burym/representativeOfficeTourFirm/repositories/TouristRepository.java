package ru.burym.representativeOfficeTourFirm.repositories;

import org.springframework.data.jdbc.repository.query.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import ru.burym.representativeOfficeTourFirm.models.entities.Tourist;
import ru.burym.representativeOfficeTourFirm.models.queries.TouristAnotherInfo;

import java.util.List;

@Repository
public interface TouristRepository extends CrudRepository<Tourist, Integer> {
    @Query("SELECT * FROM Tourist WHERE NOT(is_child)")
    List<Tourist> findAllAdult();

    @Query("SELECT count(*) as cnt FROM Tourist_Group tg " +
            "JOIN \"Group\" g ON tg.group_id = g.group_id " +
            "JOIN Flight f ON g.flight_to = f.flight_id " +
            "WHERE tg.tourist_id  = :touristId AND f.date_time <= current_date")
    int countVisitsByTouristId(int touristId);

    @Query("SELECT f_t.date_time as date_to, f_b.date_time as date_back FROM Tourist_Group tg JOIN \"Group\" g ON tg.group_id = g.group_id " +
            "JOIN Flight f_t ON g.flight_to = f_t.flight_id " +
            "JOIN Flight f_b ON g.flight_back = f_b.flight_id " +
            "WHERE tg.tourist_id = :touristId")
    List<TouristAnotherInfo.DatePair> getArrAndDepDatesByTouristId(int touristId);

    @Query("SELECT DISTINCT h.hotel_name FROM Accommodation a JOIN Hotel_Room h ON a.hotel_room_id = h.id " +
            "JOIN Tourist_Group tg ON a.tourist_group_id = tg.id " +
            "WHERE tg.tourist_id = :touristId")
    List<String> getHotelsByTouristId(int touristId);


    @Query("SELECT e.excursion_id, e.name, te.visit_date, a.name as agency " +
            "FROM Tourist_Excursion te " +
            "JOIN Excursion e ON te.excursion_id = e.excursion_id " +
            "JOIN Excursions_agency a ON e.agency_id = a. agency_id " +
            "JOIN tourist_group tg ON te.tourist_group_id = tg.id " +
            "WHERE tg.tourist_id = :touristId")
    List<TouristAnotherInfo.VisistedExcursion> getVisitedExcursionsByTouristId(int touristId);
}
