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
public class TouristExcursionController {

    private final ExcursionService excursionService;

    private final TouristExcursionService touristExcursionService;

    @Autowired
    public TouristExcursionController(ExcursionService excursionService, TouristExcursionService touristExcursionService) {
        this.excursionService = excursionService;
        this.touristExcursionService = touristExcursionService;
    }

    @GetMapping("/excursions/{id}/sign-up")
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

    @PostMapping("/excursions/{id}/sign-up")
    public String signUp(@PathVariable("id") int excId, @ModelAttribute("touristExcursion") TouristExcursion touristExcursion) {
        touristExcursionService.save(touristExcursion);
        return "redirect:/excursions/" + excId +"/sign-up?dt=" + touristExcursion.getVisitDate();
    }
}
