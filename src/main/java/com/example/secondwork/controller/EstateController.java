package com.example.secondwork.controller;

import com.example.secondwork.model.Estate;
import com.example.secondwork.repository.EstateRepository;
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
@RequestMapping("/estates")
public class EstateController {
    private final EstateRepository estateRepository;
    @Autowired
    public EstateController(EstateRepository estateRepository) {
        this.estateRepository = estateRepository;
    }

    @GetMapping()
    public String getDeals(Model model) {
        List<Estate> estates = estateRepository.findAll(Sort.by(Sort.Order.asc("id")));
        model.addAttribute("estates", estates);
        return "estate/estate/index";
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public String getEstate(Model model, @PathVariable("id") int id) {
        Estate estate = estateRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("This estate does not exist! ->  " + id));
        model.addAttribute("estate", estate);
        return "estate/estate/show";
    }

    @RequestMapping(value = "/create", method = RequestMethod.GET)
    public String create(Estate estate) {
        return "estate/estate/add";
    }

    @RequestMapping(value = "/store", method = RequestMethod.POST)
    public String store(@Valid Estate estate, BindingResult result, Model model) {
        if (result.hasErrors()) {
            return "estate/estate/add";
        }
        estateRepository.save(estate);
        model.addAttribute("estate", estateRepository.findAll());
        return "redirect:/estates";
    }

    @RequestMapping(value = "/edit/{id}", method = RequestMethod.GET)
    public String getCat(Model model, @PathVariable("id") int id) {
        Estate estate = estateRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("This estate does not exist! ->  " + id));
        model.addAttribute("estate", estate);
        return "estate/estate/update";
    }

    @RequestMapping(value = "/update/{id}", method = RequestMethod.POST)
    public String updateEstate(@PathVariable("id") int id, @Valid Estate estate, BindingResult result, Model model) {
        if (result.hasErrors()) {
            return "estate/estate/update";
        }

        Estate existingEstate = estateRepository.findById(id).orElse(null);

        if (existingEstate == null) {
            return "redirect:/estates";
        }

        existingEstate.setAddress(estate.getAddress());
        existingEstate.setBedrooms(estate.getBedrooms());
        existingEstate.setBathrooms(estate.getBathrooms());
        existingEstate.setPrice(estate.getPrice());

        estateRepository.save(existingEstate);

        return "redirect:/estates";
    }
}
