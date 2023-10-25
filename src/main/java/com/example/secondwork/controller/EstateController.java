package com.example.secondwork.controller;

import com.example.secondwork.model.Estate;
import com.example.secondwork.repository.EstateRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;

@Controller
@RequestMapping("/estates")
public class EstateController {
    private final EstateRepository estateRepository;
    @Autowired
    public EstateController(EstateRepository estateRepository) {
        this.estateRepository = estateRepository;
    }

    @GetMapping()
    public String getDeals(Model model) {
        List<Estate> estates = estateRepository.findAll();
        model.addAttribute("estates", estates);
        return "estate/estate/index";
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public String getEstate(Model model, @PathVariable("id") int id) {
        Estate estate = estateRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("This estate does not exist! ->  " + id));
        model.addAttribute("estate", estate);
        return "estate/estate/show";
    }
}
