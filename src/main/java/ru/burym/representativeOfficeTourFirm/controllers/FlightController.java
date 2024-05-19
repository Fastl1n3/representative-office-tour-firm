package ru.burym.representativeOfficeTourFirm.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import ru.burym.representativeOfficeTourFirm.models.entities.Flight;
import ru.burym.representativeOfficeTourFirm.services.FlightService;

import java.util.List;

@Controller
public class FlightController {

    private final FlightService flightService;

    @Autowired
    public FlightController(FlightService flightService) {
        this.flightService = flightService;
    }

    @GetMapping("/flights")
    public String showAll(Model model) {
        List<Flight> flightList = flightService.findAll();
        model.addAttribute("flights", flightList);
        return "flights";
    }

}
