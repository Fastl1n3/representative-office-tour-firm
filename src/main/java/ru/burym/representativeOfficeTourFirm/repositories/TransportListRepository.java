package ru.burym.representativeOfficeTourFirm.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import ru.burym.representativeOfficeTourFirm.models.entities.TransportList;

@Repository
public interface TransportListRepository extends CrudRepository<TransportList, Integer> {

}
