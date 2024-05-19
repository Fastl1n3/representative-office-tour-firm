package ru.burym.representativeOfficeTourFirm.repositories;

import org.springframework.data.jdbc.repository.query.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import ru.burym.representativeOfficeTourFirm.models.entities.Accommodation;
import ru.burym.representativeOfficeTourFirm.models.queries.TouristWithAccommodation;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface AccommodationRepository extends CrudRepository<Accommodation, Integer> {

    @Query("SELECT * FROM Accommodation a " +
            "JOIN Hotel_room h ON a.hotel_room_id = h.id " +
            "JOIN tourist_group tg on a.tourist_group_id = tg.id " +
            "JOIN Tourist t on tg.tourist_id = t.tourist_id " +
            "WHERE tg.group_id = :groupId")
    List<TouristWithAccommodation> getTouristAccommodationByGroupId(int groupId);

    @Query("SELECT count(*) as num_tourists FROM Accommodation " +
            "WHERE check_in_date BETWEEN :startDate AND :endDate")
    int getNumTouristsByDate(LocalDateTime startDate, LocalDateTime endDate);

    @Query("SELECT count(*) as num_tourists FROM Accommodation a " +
            "JOIN Tourist_Group tg ON a.tourist_group_id = tg.id " +
            "WHERE tg.tourist_type = :touristType AND check_in_date BETWEEN :startDate AND :endDate")
    int getNumTouristsByDateAndType(LocalDateTime startDate, LocalDateTime endDate, String touristType);
}
