package ru.burym.representativeOfficeTourFirm.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import ru.burym.representativeOfficeTourFirm.models.entities.Tourist;
import ru.burym.representativeOfficeTourFirm.services.TouristService;

import java.util.List;

@Controller
@RequestMapping("/tourists")
public class TouristController {

    private final TouristService touristService;

    @Autowired
    public TouristController(TouristService touristService) {
        this.touristService = touristService;
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


}
