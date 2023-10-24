package com.example.secondwork.controller;

import com.example.secondwork.CrudController;
import com.example.secondwork.dao.ShowingScheduleDAO;
import com.example.secondwork.model.ShowingSchedule;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/showing-schedules")
public class ShowingScheduleController {

    private final ShowingScheduleDAO showingScheduleDAO;
    CrudController _crudController;


    @Autowired
    public ShowingScheduleController(ShowingScheduleDAO showingScheduleDAO) {
        this.showingScheduleDAO = showingScheduleDAO;
        _crudController = new CrudController(showingScheduleDAO);
    }

    @GetMapping()
    public String index(Model model) {
        List<ShowingSchedule> showingSchedules = showingScheduleDAO.index();
        model.addAttribute("showingSchedules", showingSchedules);
        return "estate/showing-schedule/index";
    }

    @GetMapping("/{id}")
    public String show(@PathVariable("id") long id, Model model) {
        ShowingSchedule showingSchedule = showingScheduleDAO.show(id);
        model.addAttribute("showingSchedule", showingSchedule);
        return "estate/showing-schedule/show";
    }
    ///
    @GetMapping("/create")
    public String create(Model model) {
        model.addAttribute("title", "Add new client");
        model.addAttribute("showingSchedule", new ShowingSchedule());
        return "estate/showing-schedule/add";
    }

    @PostMapping("/store")
    public String store(Model model, @Valid @ModelAttribute("showingSchedule") ShowingSchedule showingSchedule) {
        _crudController.insert(showingSchedule);
        return "redirect:/showing-schedules";
    }
    //

    @GetMapping("/edit/{id}")
    public String edit(@PathVariable("id") int id, Model model) {
        ShowingSchedule showingSchedule = showingScheduleDAO.show(id);
        if (showingSchedule == null) {
            return "redirect:/clients";
        }
        model.addAttribute("showingSchedule", showingSchedule);
        model.addAttribute("title", "Editing Showing Schedule");
        return "estate/showing-schedule/update";
    }

    @PostMapping("/update/{id}")
    public String update(@PathVariable("id") int id, @Valid @ModelAttribute("client") ShowingSchedule showingSchedule) {
        showingSchedule.setId(id);
        _crudController.update(showingSchedule);
        return "redirect:/showing-schedules";
    }

    @PostMapping("/delete/{id}")
    String delete(Model model, @PathVariable("id") int id){
        ShowingSchedule showingSchedule = showingScheduleDAO.show(id);
        if(showingSchedule == null){
            return "redirect:/showing-schedules";
        }
        showingSchedule.setId(id);
        _crudController.delete(showingSchedule);
        return "redirect:/showing-schedules";
    }
}
