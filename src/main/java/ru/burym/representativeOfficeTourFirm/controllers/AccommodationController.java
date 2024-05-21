package ru.burym.representativeOfficeTourFirm.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import ru.burym.representativeOfficeTourFirm.models.entities.Accommodation;
import ru.burym.representativeOfficeTourFirm.models.entities.Group;
import ru.burym.representativeOfficeTourFirm.services.AccommodationService;
import ru.burym.representativeOfficeTourFirm.services.GroupService;
import ru.burym.representativeOfficeTourFirm.services.HotelRoomService;

import java.time.LocalDateTime;

@Controller
@RequestMapping("/accommodation")
public class AccommodationController {

    private final GroupService groupService;

    private final HotelRoomService hotelRoomService;

    private final AccommodationService accommodationService;

    @Autowired
    public AccommodationController(GroupService groupService, HotelRoomService hotelRoomService, AccommodationService accommodationService) {
        this.groupService = groupService;
        this.hotelRoomService = hotelRoomService;
        this.accommodationService = accommodationService;
    }

    @GetMapping()
    public String index() {
        return "accommodation/accommodation";
    }

    @GetMapping("/new")
    public String newAccommodation(Model model) {
        model.addAttribute("groups", groupService.findAll());
        model.addAttribute("selectedGroup", new Group());

        return "accommodation/selectGroup4cr";
    }

    @PostMapping("/new")
    public String newHandleGroup(@ModelAttribute("selectedGroup") Group group, Model model) {
        model.addAttribute("accommodation", new Accommodation());
        model.addAttribute("tourists", groupService.findTouristsForAccommodationByGroupId(group.getGroupId()));
        model.addAttribute("hotels", hotelRoomService.findAll());

        return "accommodation/create";
    }

    @PostMapping("/create")
    public String create(@ModelAttribute("accommodation") Accommodation accommodation) {
        accommodationService.save(accommodation);
        return "redirect:/accommodation";
    }

    @GetMapping("/show")
    public String show(Model model) {
        model.addAttribute("groups", groupService.findAll());
        model.addAttribute("selectedGroup", new Group());

        return "accommodation/selectGroup4sh";
    }

    @PostMapping("/show")
    public String showHandleGroup(@ModelAttribute("selectedGroup") Group group, Model model) {

        model.addAttribute("accommodations", accommodationService.getTouristAccommodationByGroupId(group.getGroupId()));
        return "accommodation/showAccommodation";
    }

    @GetMapping("/count-hotels")
    public String getCountHotels() {
        return "accommodation/countHotels";
    }

    @PostMapping("/count-hotels")
    public String showCountHotels(@ModelAttribute("start") LocalDateTime start, @ModelAttribute("end") LocalDateTime end, Model model) {
        model.addAttribute("hotels", hotelRoomService.getCountHotelsByDate(start, end));

        return "accommodation/showCountHotels";
    }

}
