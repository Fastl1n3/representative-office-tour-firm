package ru.burym.representativeOfficeTourFirm.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.burym.representativeOfficeTourFirm.models.entities.Tourist;
import ru.burym.representativeOfficeTourFirm.services.AccommodationService;
import ru.burym.representativeOfficeTourFirm.services.TouristService;

import java.time.LocalDateTime;
import java.util.List;

@Controller
@RequestMapping("/tourists")
public class TouristController {

    private final TouristService touristService;

    private final AccommodationService accommodationService;

    @Autowired
    public TouristController(TouristService touristService, AccommodationService accommodationService) {
        this.touristService = touristService;
        this.accommodationService = accommodationService;
    }

    @GetMapping()
    public String showAll(Model model) {
        List<Tourist> touristList = touristService.findAll();
        model.addAttribute("tourists", touristList);
        return "tourists/tourists";
    }

    @GetMapping("/{id}")
    public String showOne(@PathVariable("id") int id, Model model) {
        model.addAttribute("tourist", touristService.findById(id));
        model.addAttribute("touristInfo", touristService.getTouristAnotherInfo(id));
        return "tourists/showTourist";
    }

    @GetMapping("/num-tourists")
    public String numTourists() {
        return "tourists/numTourists";
    }

    @PostMapping("/num-tourists")
    public String showNumTourists(@ModelAttribute("start")LocalDateTime start, @ModelAttribute("end") LocalDateTime end, @ModelAttribute("type") String type, Model model) {
        if (type.equals("all")) {
            model.addAttribute("numTourists", accommodationService.getNumTouristsByDate(start, end));
        }
        else {
            model.addAttribute("numTourists", accommodationService.getNumTouristsByDateAndType(start, end, type));
        }

        return "tourists/showNumTourists";
    }

}
