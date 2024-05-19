package ru.burym.representativeOfficeTourFirm.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.burym.representativeOfficeTourFirm.models.entities.Excursion;
import ru.burym.representativeOfficeTourFirm.models.entities.ExcursionSchedule;
import ru.burym.representativeOfficeTourFirm.services.ExcursionService;

import java.time.LocalDateTime;
import java.util.ArrayList;

@Controller
@RequestMapping("/excursions")
public class ExcursionController {

    private final ExcursionService excursionService;

    @Autowired
    public ExcursionController(ExcursionService excursionService) {
        this.excursionService = excursionService;
    }

    @GetMapping()
    public String showAllWithAgencies(Model model) {
        model.addAttribute("excursions", excursionService.findAllWithAgencies());
        return "excursions/excursions";
    }

    @GetMapping("/new")
    public String newExcursion(Model model) {
        model.addAttribute("excursion", new Excursion());
        model.addAttribute("agencies", excursionService.findAllAgencies());
        return "excursions/createExcursion";
    }

    @PostMapping("/create")
    public String create(@ModelAttribute("excursion") Excursion excursion) {
        excursionService.save(excursion);
        return "redirect:/excursions";
    }


    @GetMapping("/{id}/edit")
    public String edit(@PathVariable("id") int id, Model model) {
        model.addAttribute("excursion", excursionService.findWithAgencyById(id));
        model.addAttribute("agencies", excursionService.findAllAgencies());

        return "excursions/editExcursion";
    }

    @PostMapping("/{id}/edit")
    public String update(@PathVariable("id") int id, @ModelAttribute("excursion") Excursion excursion) {
        excursion.setExcursionId(id);
        excursionService.update(excursion);

        return "redirect:/excursions/" + id;
    }

    @GetMapping("/{id}/delete")
    public String delete(@PathVariable("id") int id) {
        excursionService.delete(id);

        return "redirect:/excursions/";
    }


    @GetMapping("/{id}")
    public String show(@PathVariable("id") int id, Model model) {
        model.addAttribute("excursion", excursionService.findWithScheduleById(id));
     //   model.addAttribute("scheduleList", new ArrayList<LocalDateTime>());
        return "excursions/showExcursion";
    }

    @GetMapping("/{id}/add-schedule")
    public String addSchedule(@PathVariable("id") int id, Model model) {
        ExcursionSchedule schedule = new ExcursionSchedule();
        schedule.setExcursionId(id);
        model.addAttribute("schedule",schedule);
        model.addAttribute("excId", id);

        return "excursions/createSchedule";
    }

    @PostMapping("/{id}/add-schedule")
    public String createSchedule(@PathVariable("id") int id, @ModelAttribute("schedule") ExcursionSchedule schedule) {
        excursionService.saveSchedule(schedule);

        return "redirect:/excursions/" + id;
    }

    @GetMapping("/{id}/delete-schedule")
    public String deleteSchedulePage(@PathVariable("id") int id, Model model) {
        model.addAttribute("excId", id);
        model.addAttribute("schedules", excursionService.findWithScheduleById(id).getScheduleList());
        return "excursions/deleteSchedule";
    }

    @GetMapping("/{id}/delete-schedule/delete")
    public String deleteSchedule(@PathVariable("id") int id, @RequestParam("dt")LocalDateTime dateTime) {
        excursionService.deleteSchedule(id, dateTime);
        return "redirect:/excursions/" + id;
    }
}
