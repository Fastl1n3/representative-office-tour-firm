package ru.burym.representativeOfficeTourFirm.repositories;

import org.springframework.data.jdbc.repository.query.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import ru.burym.representativeOfficeTourFirm.models.entities.Tourist;
import ru.burym.representativeOfficeTourFirm.models.entities.TouristGroup;
import ru.burym.representativeOfficeTourFirm.models.outputs.TouristAccomOutput;
import ru.burym.representativeOfficeTourFirm.models.outputs.TouristExcurOutput;
import ru.burym.representativeOfficeTourFirm.models.outputs.TouristWithTypeOutput;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Repository
public interface TouristGroupRepository extends CrudRepository<TouristGroup, Integer> {

    @Query("SELECT t.tourist_id, t.firstname, t.lastname, t.patronymic, t.date_of_birth, t.passport_id, tg.tourist_type " +
            "FROM Tourist t JOIN Tourist_Group tg ON t.tourist_id = tg.tourist_id " +
            "WHERE tg.group_id = :id")
    List<TouristWithTypeOutput> findTouristsByGroupId(int id);

    @Query("SELECT t.tourist_id, t.firstname, t.lastname, t.patronymic, t.passport_id " +
            "FROM tourist t JOIN tourist_group tg ON t.tourist_id = tg.tourist_id " +
            "WHERE tg.group_id != :id")
    List<Tourist> findTouristsNotInGroup(int id);

    @Query("SELECT tg.id as tourist_group_id, t.firstname, t.lastname, t.patronymic, t.passport_id FROM tourist_group tg " +
            "JOIN Tourist t on tg.tourist_id = t.tourist_id " +
            "WHERE tg.group_id = :groupId AND (NOT EXISTS(SELECT * FROM accommodation a WHERE a.tourist_group_id = tg.id))" )
    List<TouristAccomOutput> findTouristsForAccommodationByGroupId(int groupId);


    @Query("SELECT tg.id as tourist_group_id, tg.tourist_type, t.firstname, t.lastname, t.patronymic, t.passport_id " +
            "FROM tourist_group tg JOIN tourist t on t.tourist_id = tg.tourist_id " +
            "JOIN \"Group\" G on G.group_id = tg.group_id " +
            "JOIN flight f_t on f_t.flight_id = G.flight_to " +
            "JOIN flight f_b on f_b.flight_id = G.flight_back " +
            "WHERE tg.tourist_type = 'cmp' AND :dateTime BETWEEN f_t.date_time AND f_b.date_time")
    List<TouristExcurOutput> findRelevantTouristsForExcursionByDate(LocalDateTime dateTime);


    @Query("SELECT campers::float8 / for_cargo * 100 as percent_cmp_to_crg FROM (SELECT SUM(CASE tourist_type WHEN 'cmp' THEN 1 ELSE 0 END) as campers, " +
            "SUM(CASE tourist_type WHEN 'crg' THEN 1 ELSE 0 END) as for_cargo " +
            "FROM Tourist_group) as cnt ")
    Optional<Double> getRatioCampersToForCargo(); // #14

    @Query("SELECT campers::float8 / for_cargo * 100 as percent_cmp_to_crg FROM " +
            "(SELECT SUM(CASE tourist_type WHEN 'cmp' THEN 1 ELSE 0 END) as campers, " +
            "        SUM(CASE tourist_type WHEN 'crg' THEN 1 ELSE 0 END) as for_cargo " +
            "FROM Tourist_group " +
            "JOIN \"Group\" g on tourist_group.group_id = g.group_id " +
            "JOIN flight f on f.flight_id = g.flight_to " +
            "WHERE f.date_time BETWEEN :startDate AND :endDate) as cnt ")
    Optional<Double> getRatioCampersToForCargoByDate(LocalDateTime startDate, LocalDateTime endDate); // #14
}
