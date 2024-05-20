package ru.burym.representativeOfficeTourFirm.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.burym.representativeOfficeTourFirm.models.entities.Group;
import ru.burym.representativeOfficeTourFirm.models.entities.TouristGroup;
import ru.burym.representativeOfficeTourFirm.services.FlightService;
import ru.burym.representativeOfficeTourFirm.services.GroupService;

import java.time.LocalDateTime;

@Controller
@RequestMapping("/groups")
public class GroupController {

    private final FlightService flightService;

    private final GroupService groupService;

    @Autowired
    public GroupController(FlightService flightService, GroupService groupService) {
        this.flightService = flightService;
        this.groupService = groupService;
    }

    @GetMapping()
    public String showAll(Model model) {
        model.addAttribute("groups", groupService.findAll());
        return "groups/groups";
    }

    @GetMapping("/new")
    public String newGroup(Model model) {
        model.addAttribute("group", new Group());
        model.addAttribute("flights", flightService.findAll());
        return "groups/createGroup";
    }

    @GetMapping("/{id}")
    public String showOne(@PathVariable("id") int id, Model model) {
        model.addAttribute("group", groupService.findById(id));
        model.addAttribute("flights", groupService.findFlightsByGroupId(id));
        model.addAttribute("people", groupService.findTouristsByGroupId(id));
        return "groups/showGroup";
    }

    @PostMapping("/create")
    public String create(@ModelAttribute("group") Group group) {
        groupService.save(group);
        return "redirect:/groups";
    }

    @GetMapping("/{id}/add")
    public String addTourist(@PathVariable("id") int id, Model model) {
        model.addAttribute("touristGroup", new TouristGroup());
        model.addAttribute("tourists", groupService.findTouristsNotInGroup(id));
        model.addAttribute("idGroup", id);
        return "groups/addTouristToGroup";
    }

    @PostMapping("/{id}/save-tourist")
    public String saveTourist(@PathVariable("id") int id, @ModelAttribute("touristGroup") TouristGroup touristGroup) {
        groupService.saveTouristToGroup(id, touristGroup);
        return "redirect:/groups/" + id;
    }


    @GetMapping("/ratio")
    public String ratioCampersToForCargo() {

        return "groups/ratio";
    }

    @PostMapping("/ratio")
    public String getRatioCampersToForCargo(@ModelAttribute("start") LocalDateTime start, @ModelAttribute("end") LocalDateTime end, Model model) {
        if (start == null || end == null) {
            model.addAttribute("ratio", groupService.getRatioCampersToForCargo());
        }
        else {
            model.addAttribute("ratio", groupService.getRatioCampersToForCargoByDate(start, end));
        }

        return "groups/showRatio";
    }

    @GetMapping("/{id}/list")
    public String listForCustoms(@PathVariable("id") int id, Model model) {
        model.addAttribute("groupId", id);
        return "groups/listForCustoms";
    }

    @PostMapping("/{id}/list")
    public String showListForCustoms(@PathVariable("id") int id, @ModelAttribute("type") String touristType, Model model) {
        model.addAttribute("groupId", id);
        if (touristType.equals("none")) {
            model.addAttribute("tourists", groupService.getTouristInfoByGroupId(id));
        }
        else {
            model.addAttribute("tourists", groupService.getTouristInfoByGroupIdAndType(id, touristType));
        }

        return "groups/showListForCustoms";
    }


}
