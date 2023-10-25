package com.example.secondwork.controller;

import com.example.secondwork.model.ShowingSchedule;
import com.example.secondwork.repository.SceduleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
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
        List<ShowingSchedule> showingSchedules = sceduleRepository.findAll();
        model.addAttribute("showingSchedules", showingSchedules);
        return "estate/showing-schedule/index";
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public String getSceduleRepository(Model model, @PathVariable("id") int id) {
        ShowingSchedule showingSchedule = sceduleRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("This showing schedule does not exist! ->  " + id));
        model.addAttribute("showingSchedules", showingSchedule);
        return "estate/showing-schedule/show";
    }


}
