package ru.burym.representativeOfficeTourFirm.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import ru.burym.representativeOfficeTourFirm.services.GroupService;
import ru.burym.representativeOfficeTourFirm.services.SpendingService;

import java.time.LocalDateTime;

@Controller
@RequestMapping("/spending")
public class SpendingController {

    private final SpendingService spendingService;

    private final GroupService groupService;

    @Autowired
    public SpendingController(SpendingService spendingService, GroupService groupService) {
        this.spendingService = spendingService;
        this.groupService = groupService;
    }

    @GetMapping()
    public String index() {
        return "spending/index";
    }

    @GetMapping("/group-report")
    public String groupReport(Model model) {
        model.addAttribute("groups", groupService.findAll());

        return "spending/groupReport";
    }

    @PostMapping("/group-report")
    public String getGroupReport(@ModelAttribute("groupId") Integer groupId, @ModelAttribute("type") String type,  Model model) {
        System.out.println("Type: " + type);
        if (type.equals("none")) {
            model.addAttribute("reports", spendingService.getGroupReport(groupId));
        }
        else {
            model.addAttribute("reports", spendingService.getGroupReportByCategory(groupId, type));
        }

        return "spending/showGroupReport";
    }

    @GetMapping("/exp-and-inc")
    public String expAndInc() {
        return "spending/expAndInc";
    }

    @PostMapping("/exp-and-inc")
    public String showExpAndInc(@ModelAttribute("start") LocalDateTime start, @ModelAttribute("end") LocalDateTime end, Model model) {
        model.addAttribute("expAndIncList", spendingService.getExpAndIncByDate(start, end));

        return "spending/showExpAndInc";
    }

    @GetMapping("/profitability")
    public String getProfitability(Model model) {
        model.addAttribute("profitabilityInfo", spendingService.getProfitabilityInfo());

        return "spending/showProfitability";
    }
}
