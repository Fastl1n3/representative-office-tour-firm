package ru.burym.representativeOfficeTourFirm.repositories;

import org.springframework.data.jdbc.repository.query.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import ru.burym.representativeOfficeTourFirm.models.entities.HotelRoom;
import ru.burym.representativeOfficeTourFirm.models.queries.HotelCountInfo;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface HotelRoomRepository extends CrudRepository<HotelRoom, Integer> {

    @Query("SELECT hotel_name, COUNT(*) num_people FROM Accommodation a JOIN Hotel_Room h ON a.hotel_room_id = h.id " +
            "WHERE a.check_in_date BETWEEN :start_date::timestamp AND :end_date::timestamp " +
            "GROUP BY hotel_name ")
    List<HotelCountInfo> getCountHotelsByDate(LocalDateTime start_date, LocalDateTime end_date); // #5
}
