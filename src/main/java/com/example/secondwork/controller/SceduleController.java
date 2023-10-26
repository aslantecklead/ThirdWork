package com.example.secondwork.controller;

import com.example.secondwork.model.ShowingSchedule;
import com.example.secondwork.repository.SceduleRepository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;

@Controller
@RequestMapping("/showing-schedules")
public class SceduleController {
    private final SceduleRepository sceduleRepository;
    @Autowired
    public SceduleController(SceduleRepository sceduleRepository) {
        this.sceduleRepository = sceduleRepository;
    }

    @GetMapping()
    public String getShowingSchedules(Model model) {
        List<ShowingSchedule> showingSchedules = sceduleRepository.findAll(Sort.by(Sort.Order.asc("id")));
        model.addAttribute("showingSchedules", showingSchedules);
        return "estate/showing-schedule/index";
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public String getSceduleRepository(Model model, @PathVariable("id") int id) {
        ShowingSchedule showingSchedule = sceduleRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("This showing schedule does not exist! ->  " + id));
        model.addAttribute("showingSchedules", showingSchedule);
        return "estate/showing-schedule/show";
    }

    @RequestMapping(value = "/create", method = RequestMethod.GET)
    public String create(ShowingSchedule showingSchedule) {
        return "estate/showing-schedule/add";
    }

    @RequestMapping(value = "/store", method = RequestMethod.POST)
    public String store(@Valid ShowingSchedule showingSchedule, BindingResult result, Model model) {
        if (result.hasErrors()) {
            return "estate/showing-schedule/add";
        }
        sceduleRepository.save(showingSchedule);
        model.addAttribute("deal", sceduleRepository.findAll());
        return "redirect:/showing-schedules";
    }

    @RequestMapping(value = "/edit/{id}", method = RequestMethod.GET)
    public String edit(Model model, @PathVariable("id") int id) {
        ShowingSchedule showingSchedule = sceduleRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("This offer does not exist! ->  " + id));
        model.addAttribute("showingSchedule", showingSchedule);
        return "estate/offer/update";
    }

    @RequestMapping(value = "/update/{id}", method = RequestMethod.POST)
    public String update(@PathVariable("id") int id, @Valid ShowingSchedule showingSchedule, BindingResult result, Model model) {
        if (result.hasErrors()) {
            return "estate/offer/update";
        }

        ShowingSchedule existingSchedule = sceduleRepository.findById(id).orElse(null);

        if (existingSchedule == null) {
            return "redirect:/showing-schedules";
        }

        existingSchedule.setProperty(showingSchedule.getProperty());
        existingSchedule.setDate(showingSchedule.getDate());
        existingSchedule.setClientName(showingSchedule.getClientName());

        sceduleRepository.save(existingSchedule);

        return "redirect:/showing-schedules";
    }

}
