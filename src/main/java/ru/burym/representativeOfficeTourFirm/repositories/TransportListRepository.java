package ru.burym.representativeOfficeTourFirm.repositories;

import org.springframework.data.jdbc.repository.query.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import ru.burym.representativeOfficeTourFirm.models.entities.TransportList;

import java.util.Optional;

@Repository
public interface TransportListRepository extends CrudRepository<TransportList, Integer> {

}
