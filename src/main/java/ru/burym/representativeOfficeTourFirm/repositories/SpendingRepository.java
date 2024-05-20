package ru.burym.representativeOfficeTourFirm.repositories;

import org.springframework.data.jdbc.repository.query.Query;
import org.springframework.data.repository.CrudRepository;
import ru.burym.representativeOfficeTourFirm.models.entities.Spending;
import ru.burym.representativeOfficeTourFirm.models.queries.ExpenseInfo;
import ru.burym.representativeOfficeTourFirm.models.queries.ProfitabilityInfo;
import ru.burym.representativeOfficeTourFirm.models.queries.SpendingReport;

import java.time.LocalDateTime;
import java.util.List;

public interface SpendingRepository extends CrudRepository<Spending, Long> {

    @Query("SELECT t.firstname, t.lastname, t.patronymic, service_type, cost, spend_date FROM \"Group\" g " +
            "JOIN Tourist_group tg on g.group_id = tg.group_id " +
            "JOIN Spending s ON tg.tourist_id = s.tourist_id " +
            "JOIN Tourist t on t.tourist_id = s.tourist_id " +
            "WHERE g.group_id = :groupId AND " +
            "spend_date BETWEEN (SELECT date_time FROM Flight WHERE flight_id = g.flight_to) AND (SELECT date_time FROM Flight WHERE flight_id = g.flight_back)")
    List<SpendingReport> getGroupReport(int groupId); // #10

    @Query("SELECT t.firstname, t.lastname, t.patronymic, service_type, cost, spend_date FROM \"Group\" g " +
            "JOIN Tourist_group tg on g.group_id = tg.group_id " +
            "JOIN Spending s ON tg.tourist_id = s.tourist_id " +
            "JOIN Tourist t on t.tourist_id = s.tourist_id " +
            "WHERE g.group_id = :groupId AND tg.tourist_type=:category AND " +
            "spend_date BETWEEN (SELECT date_time FROM Flight WHERE flight_id = g.flight_to) AND (SELECT date_time FROM Flight WHERE flight_id = g.flight_back)")
    List<SpendingReport> getGroupReportByCategory(int groupId, String category); // #10

    @Query("SELECT spend_date, s.service_type, cost as expense, ROUND(cost * (100 + sf.fee) / 100, 2) as income,  ROUND(cost * sf.fee / 100, 2) as profit " +
            "FROM Spending s JOIN Service_fee sf on sf.service_type = s.service_type " +
            "WHERE spend_date BETWEEN :startDate AND :endDate ")
    List<ExpenseInfo> getExpAndIncByDate(LocalDateTime startDate, LocalDateTime endDate); // #11

    @Query("SELECT expense, ROUND(income, 2) as income, ROUND(income / expense, 2) as efficiency FROM (SELECT SUM(cost) as expense, SUM(cost * (100 + sf.fee) / 100) as income " +
            "FROM Spending s JOIN Service_fee sf on sf.service_type = s.service_type) as ei ")
    ProfitabilityInfo getProfitabilityInfo(); // #13
}
