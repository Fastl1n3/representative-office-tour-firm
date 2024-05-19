package ru.burym.representativeOfficeTourFirm.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import ru.burym.representativeOfficeTourFirm.services.SpendingService;

@Controller
@RequestMapping("/spending")
public class SpendingController {

    private final SpendingService spendingService;

    @Autowired
    public SpendingController(SpendingService spendingService) {
        this.spendingService = spendingService;
    }

    @GetMapping("/group-report")
    public String getReportGroup() {

        return "spending/groupReport";
    }
}
