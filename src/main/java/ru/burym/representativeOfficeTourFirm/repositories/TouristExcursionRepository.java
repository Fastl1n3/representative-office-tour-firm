package ru.burym.representativeOfficeTourFirm.repositories;

import org.springframework.data.jdbc.repository.query.Modifying;
import org.springframework.data.jdbc.repository.query.Query;
import org.springframework.data.repository.CrudRepository;
import ru.burym.representativeOfficeTourFirm.models.entities.TouristExcursion;
import ru.burym.representativeOfficeTourFirm.models.outputs.TouristExcurOutput;

import java.time.LocalDateTime;
import java.util.List;

public interface TouristExcursionRepository extends CrudRepository<TouristExcursion, TouristExcursion.TouristExcursionPK> {

    @Modifying
    @Query("INSERT INTO tourist_excursion(tourist_group_id, excursion_id, visit_date) " +
            "VALUES (:#{#touristExcursion.touristExcursionPK.touristGroupId}, :#{#touristExcursion.touristExcursionPK.excursionId}, :#{#touristExcursion.visitDate})")
    void saveEntity(TouristExcursion touristExcursion);


    @Query("SELECT * FROM tourist_excursion te " +
            "JOIN tourist_group tg on tg.id = te.tourist_group_id " +
            "JOIN tourist t on t.tourist_id = tg.tourist_id " +
            "WHERE te.excursion_id = :excursionId AND te.visit_date = :visitDate")
    List<TouristExcurOutput> findTouristsOnExcursion(int excursionId, LocalDateTime visitDate);

    @Query("SELECT COUNT(*) num_people FROM Tourist_Excursion " +
            "WHERE visit_date BETWEEN :start_date::timestamp AND :end_date::timestamp ")
    Integer getCountTouristsByDate(LocalDateTime start_date, LocalDateTime end_date); // #6
}
