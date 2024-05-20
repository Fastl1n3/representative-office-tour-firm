package ru.burym.representativeOfficeTourFirm.repositories;

import org.springframework.data.jdbc.repository.query.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import ru.burym.representativeOfficeTourFirm.models.entities.ExcursionsAgency;

import java.util.List;

@Repository
public interface ExcursionsAgencyRepository extends CrudRepository<ExcursionsAgency, Integer> {

    @Query("SELECT name, rating FROM Excursions_agency " +
            "WHERE rating = (SELECT MAX(rating) FROM Excursions_agency) ")
    List<ExcursionsAgency> getBestAgency(); // #7
}
