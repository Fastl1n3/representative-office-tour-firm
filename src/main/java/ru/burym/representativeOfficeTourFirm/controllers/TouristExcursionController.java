package ru.burym.representativeOfficeTourFirm.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.burym.representativeOfficeTourFirm.models.entities.TouristExcursion;
import ru.burym.representativeOfficeTourFirm.services.ExcursionService;
import ru.burym.representativeOfficeTourFirm.services.TouristExcursionService;

import java.time.LocalDateTime;

@Controller
@RequestMapping("/excursions")
public class TouristExcursionController {

    private final TouristExcursionService touristExcursionService;

    @Autowired
    public TouristExcursionController(TouristExcursionService touristExcursionService) {
        this.touristExcursionService = touristExcursionService;
    }

    @GetMapping("/{id}/sign-up")
    public String signUp(@PathVariable("id") int excId, @RequestParam("dt") LocalDateTime dateTime, Model model) {
        TouristExcursion touristExcursion = new TouristExcursion();
        touristExcursion.setExcursionId(excId);
        touristExcursion.setVisitDate(dateTime);

        model.addAttribute("touristExcursion", touristExcursion);
        model.addAttribute("excId", excId);

        model.addAttribute("tourists", touristExcursionService.findRelevantTouristsByDate(dateTime));
        model.addAttribute("signedTourists", touristExcursionService.findSignedTourists(excId, dateTime));
        return "excursions/signUpTourist";
    }

    @PostMapping("/{id}/sign-up")
    public String signUp(@PathVariable("id") int excId, @ModelAttribute("touristExcursion") TouristExcursion touristExcursion) {
        touristExcursionService.save(touristExcursion);
        return "redirect:/excursions/" + excId +"/sign-up?dt=" + touristExcursion.getVisitDate();
    }

    @GetMapping("/num-people")
    public String getNumPeople() {
        return "excursions/numPeople";
    }

    @PostMapping("/num-people")
    public String showNumPeople(@ModelAttribute("start") LocalDateTime start, @ModelAttribute("end") LocalDateTime end, Model model) {
        model.addAttribute("num", touristExcursionService.getCountTouristsByDate(start, end));

        return "excursions/showNumPeople";
    }

}
