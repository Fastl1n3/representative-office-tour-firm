package ru.burym.representativeOfficeTourFirm.repositories;

import org.springframework.data.jdbc.repository.query.Modifying;
import org.springframework.data.jdbc.repository.query.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import ru.burym.representativeOfficeTourFirm.models.entities.Excursion;
import ru.burym.representativeOfficeTourFirm.models.entities.ExcursionSchedule;
import ru.burym.representativeOfficeTourFirm.models.outputs.ExcursionOutput;
import ru.burym.representativeOfficeTourFirm.models.queries.MostPopularExcursion;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Repository
public interface ExcursionRepository extends CrudRepository<Excursion, Integer> {

    @Query("SELECT e.excursion_id, e.name, e.description, e.price, e.duration, e.agency_id, a.name as agency_name, a.rating " +
            "FROM excursion e JOIN excursions_agency a ON e.agency_id = a.agency_id")
    List<ExcursionOutput> findAllWithAgencies();

    @Query("SELECT e.excursion_id, e.name, e.description, e.price, e.duration, e.agency_id, a.name as agency_name, a.rating " +
            "FROM excursion e JOIN excursions_agency a ON e.agency_id = a.agency_id " +
            "WHERE e.excursion_id = :id")
    Optional<ExcursionOutput> findWithAgencyById(int id);

    @Query("SELECT * FROM excursion_schedule WHERE excursion_id = :id")
    List<ExcursionSchedule> findScheduleByExcursionId(int id);

    @Modifying
    @Query("INSERT INTO excursion_schedule (excursion_id, date_time) VALUES (:#{#schedule.excursionSchedulePK.excursionId}, :#{#schedule.excursionSchedulePK.dateTime})")
    void saveSchedule(ExcursionSchedule schedule);

    @Modifying
    @Query("DELETE FROM excursion_schedule WHERE excursion_id = :id AND date_time = :dateTime")
    void deleteSchedule(int id, LocalDateTime dateTime);

    @Query("WITH cnts as (SELECT excursion_id, COUNT(*) as cnt FROM Tourist_Excursion " +
            "              GROUP BY excursion_id),\n" +
            "     max_people as (SELECT MAX(cnt) as max_p FROM cnts) " +
            "SELECT e.excursion_id, e.name, ea.name agency " +
            "FROM max_people, cnts JOIN Excursion e ON cnts.excursion_id = e.excursion_id " +
            "    JOIN Excursions_agency ea ON e.agency_id = ea.agency_id " +
            "WHERE cnt = max_p ")
    List<MostPopularExcursion> getMostPopular();


}
