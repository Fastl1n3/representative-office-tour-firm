package ru.burym.representativeOfficeTourFirm.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.burym.representativeOfficeTourFirm.models.entities.Storage;
import ru.burym.representativeOfficeTourFirm.services.StorageService;
import ru.burym.representativeOfficeTourFirm.services.TouristService;

import java.time.LocalDateTime;

@Controller
@RequestMapping("/storage")
public class StorageController {

    private final StorageService storageService;

    private final TouristService touristService;

    @Autowired
    public StorageController(StorageService storageService, TouristService touristService) {
        this.storageService = storageService;
        this.touristService = touristService;
    }

    @GetMapping()
    public String showAll(Model model) {
        model.addAttribute("cargoList", storageService.findAll());

        return "storage/storage";
    }

    @GetMapping("/new")
    public String newCargo(Model model) {
        model.addAttribute("cargo", new Storage());
        model.addAttribute("owners", touristService.findAllAdult());

        return "storage/createCargo";
    }

    @PostMapping("/new")
    public String create(@ModelAttribute("cargo") Storage storage) {
        storageService.save(storage);

        return "redirect:/storage";
    }

    @GetMapping("/{id}")
    public String showCargo(@PathVariable("id") int id, Model model) {
        model.addAttribute("cargoWithOwner", storageService.findCargoWithOwnerById(id));


        return "storage/showCargo";
    }

    @GetMapping("/stat/cargo-types")
    public String showCargoTypesStat(Model model) {
        model.addAttribute("cargoTypes", storageService.getCargoTypeStat());

        return "storage/showCargoTypesStat";
    }

    @GetMapping("/stat/turnover")
    public String turnover() {

        return "storage/turnover";
    }

    @PostMapping("/stat/turnover")
    public String showTurnover(@ModelAttribute("start") LocalDateTime start, @ModelAttribute("end") LocalDateTime end, Model model) {
        model.addAttribute("storageStat", storageService.getStorageStatByDate(start, end));

        return "storage/showTurnover";
    }

}
