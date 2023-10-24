package com.example.secondwork.controller;

import com.example.secondwork.CrudController;
import com.example.secondwork.dao.EstateDAO;
import com.example.secondwork.model.Estate;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/estates")
public class EstateController {
    private EstateDAO _estateDAO;
    CrudController _crudController;

    @Autowired
    public EstateController(EstateDAO estateDAO) {
        _estateDAO = estateDAO;
        _crudController = new CrudController(_estateDAO);
    }

    @GetMapping()
    public String index(Model model) {
        model.addAttribute("estates", _estateDAO.index());
        return "estate/estate/index";
    }

    @GetMapping("/{id}")
    public String show(@PathVariable("id") int id, Model model) {
        model.addAttribute("estate", _estateDAO.show(id));
        return "estate/estate/show";
    }

    @GetMapping("/create")
    public String create(Model model) {
        model.addAttribute("title", "Add new estate");
        model.addAttribute("estate", new Estate());
        return "estate/estate/add";
    }

    @PostMapping("/store")
    public String store(Model model, @Valid @ModelAttribute("client") Estate estate) {
        _crudController.insert(estate);
        return "redirect:/estates";
    }

    @GetMapping("/edit/{id}")
    public String edit(@PathVariable("id") int id, Model model) {
        Estate estate = _estateDAO.show(id);
        if (estate == null) {
            return "redirect:/estates";
        }
        model.addAttribute("estate", estate);
        model.addAttribute("title", "Editing Estate");
        return "estate/estate/update";
    }

    @PostMapping("/update/{id}")
    public String update(@PathVariable("id") int id, @Valid @ModelAttribute("estate") Estate estate) {
        estate.setId(id);
        _crudController.update(estate);
        return "redirect:/estates";
    }

    @PostMapping("/delete/{id}")
    String delete(Model model, @PathVariable("id") int id){
        Estate estate = _estateDAO.show(id);
        if(estate == null){
            return "redirect:/clients";
        }
        estate.setId(id);
        _crudController.delete(estate);
        return "redirect:/estates";
    }
}