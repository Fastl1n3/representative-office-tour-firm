package ru.burym.representativeOfficeTourFirm.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.burym.representativeOfficeTourFirm.models.entities.Group;
import ru.burym.representativeOfficeTourFirm.models.entities.Tourist;
import ru.burym.representativeOfficeTourFirm.models.entities.TouristGroup;
import ru.burym.representativeOfficeTourFirm.services.FlightService;
import ru.burym.representativeOfficeTourFirm.services.GroupService;

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
        var groups = groupService.findAll();

        model.addAttribute("groups", groups);
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




}
