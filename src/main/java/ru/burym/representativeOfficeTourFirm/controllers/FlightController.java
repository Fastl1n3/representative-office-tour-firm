package ru.burym.representativeOfficeTourFirm.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import ru.burym.representativeOfficeTourFirm.models.entities.Flight;
import ru.burym.representativeOfficeTourFirm.services.FlightService;

import java.util.List;

@Controller
@RequestMapping("/flights")
public class FlightController {

    private final FlightService flightService;

    @Autowired
    public FlightController(FlightService flightService) {
        this.flightService = flightService;
    }

    @GetMapping()
    public String showAll(Model model) {
        List<Flight> flightList = flightService.findAll();
        model.addAttribute("flights", flightList);
        return "flights/flights";
    }

    @GetMapping("/{id}")
    public String showFlightStat(@PathVariable("id") int id, Model model) {
        model.addAttribute("flight", flightService.findById(id));
        model.addAttribute("workloadOpt", flightService.getFlightWorkload(id));
        model.addAttribute("touristInfo", flightService.getTouristInfoByFlightId(id));
        model.addAttribute("cargoInfo", flightService.getCargoInfoByFlight(id));

        return "flights/showFlightStat";
    }
}
