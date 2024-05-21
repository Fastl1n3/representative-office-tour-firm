package ru.burym.representativeOfficeTourFirm.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.burym.representativeOfficeTourFirm.models.entities.TransportList;
import ru.burym.representativeOfficeTourFirm.services.FlightService;
import ru.burym.representativeOfficeTourFirm.services.StorageService;
import ru.burym.representativeOfficeTourFirm.services.TouristService;
import ru.burym.representativeOfficeTourFirm.services.TransportListService;

@Controller
@RequestMapping("/transport-lists")
public class TransportListController {

    private final TransportListService transportListService;

    private final FlightService flightService;

    private final StorageService storageService;

    private final TouristService touristService;

    @Autowired
    public TransportListController(TransportListService transportListService, FlightService flightService, StorageService storageService, TouristService touristService) {
        this.transportListService = transportListService;
        this.flightService = flightService;
        this.storageService = storageService;
        this.touristService = touristService;
    }

    @GetMapping()
    public String showAll(Model model) {
        model.addAttribute("transportLists", transportListService.findAll());
        model.addAttribute("tourists", touristService.findAllAdult());

        return "transportLists/transportLists";
    }

    @PostMapping("/new")
    public String newTransportList(@RequestParam("touristId") int touristId, Model model) {
        TransportList transportList = new TransportList();
        transportList.setOwnerId(touristId);

        model.addAttribute("transportList", transportList);
        model.addAttribute("flights", flightService.findAll());
        model.addAttribute("cargoes", storageService.findWithoutTransportListByTouristId(touristId));

        return "transportLists/createTransportList";
    }

    @PostMapping("/new/create")
    public String create(@ModelAttribute("transportList") TransportList transportList) {
        transportListService.save(transportList);

        return "redirect:/transport-lists";
    }

    @GetMapping("/{id}")
    public String showTransportList(@PathVariable("id") int id, Model model) {
        model.addAttribute("tlOut", transportListService.findWithCargoById(id));

        return "transportLists/showTransportList";
    }

}
