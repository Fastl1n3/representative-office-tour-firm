package ru.burym.representativeOfficeTourFirm.repositories;

import org.springframework.data.jdbc.repository.query.Modifying;
import org.springframework.data.jdbc.repository.query.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import ru.burym.representativeOfficeTourFirm.models.entities.Storage;
import ru.burym.representativeOfficeTourFirm.models.queries.CargoTypeStat;
import ru.burym.representativeOfficeTourFirm.models.queries.StorageStat;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface StorageRepository extends CrudRepository<Storage, Integer> {

    List<Storage> findByOwnerId(int ownerId);

    List<Storage> findByTransportListId(int transportListId);

    @Modifying
    @Query("UPDATE storage SET transport_list_id = :transportListId WHERE cargo_id = :cargoId")
    void setTransportListById(int cargoId, int transportListId);

    @Query("SELECT * FROM storage WHERE transport_list_id IS NULL AND owner_id = :touristId")
    List<Storage> findWithoutTransportListByTouristId(int touristId);


    @Query("WITH w_and_n as ( " +
            "SELECT SUM(weight) as weight, COUNT(cargo_id) as num_seats FROM Storage " +
            "WHERE delivery_date BETWEEN :start_date AND :end_date), " +
            "planes as ( " +
            "SELECT DISTINCT f.flight_id, f.plane_type FROM Storage s " +
            "JOIN Transport_list tl on tl.list_id = s.transport_list_id " +
            "JOIN flight f on tl.flight_id = f.flight_id " +
            "WHERE delivery_date BETWEEN :start_date AND :end_date), " +
            "num_planes as ( " +
            "SELECT SUM(CASE plane_type WHEN 'C' THEN 1 ELSE 0 END) as cargo_planes, " +
            "SUM(CASE plane_type WHEN 'CP' THEN 1 ELSE 0 END) as cargo_pass_planes " +
            "FROM planes) " +
            "SELECT num_seats, weight, cargo_planes, cargo_pass_planes, cargo_planes + cargo_pass_planes as all_planes " +
            "FROM w_and_n, num_planes ")
    StorageStat getStorageStatByDate(LocalDateTime start_date, LocalDateTime end_date); // #9

    @Query("SELECT marking, COUNT(*) as num_cargo, ROUND(COUNT(*)::numeric / (SELECT COUNT(*) FROM Storage), 2) as fraction FROM Storage " +
            "GROUP BY marking ")
    List<CargoTypeStat> getCargoTypeStat(); // #12
}
