package com.example.secondwork.controller;

import com.example.secondwork.model.Deal;
import com.example.secondwork.repository.DealRepository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/deals")
public class DealController {
    private final DealRepository dealRepository;
    @Autowired
    public DealController(DealRepository dealRepository) {
        this.dealRepository = dealRepository;
    }

    @GetMapping()
    public String getDeals(Model model) {
        List<Deal> deal = dealRepository.findAll(Sort.by(Sort.Order.asc("id")));
        model.addAttribute("deals", deal);
        return "estate/deal/index";
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public String getDeal(Model model, @PathVariable("id") int id) {
        Deal deal = dealRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("This deal does not exist! ->  " + id));
        model.addAttribute("deal", deal);
        return "estate/deal/show";
    }

    @RequestMapping(value = "/create", method = RequestMethod.GET)
    public String create(Deal deal) {
        return "estate/deal/add";
    }

    @RequestMapping(value = "/store", method = RequestMethod.POST)
    public String store(@Valid Deal deal, BindingResult result, Model model) {
        if (result.hasErrors()) {
            return "estate/deal/add";
        }
        dealRepository.save(deal);
        model.addAttribute("deal", dealRepository.findAll());
        return "redirect:/deals";
    }

    @RequestMapping(value = "/edit/{id}", method = RequestMethod.GET)
    public String getCat(Model model, @PathVariable("id") int id) {
        Deal deal = dealRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("This deal does not exist! ->  " + id));
        model.addAttribute("deal", deal);
        return "estate/deal/update";
    }

    @RequestMapping(value = "/update/{id}", method = RequestMethod.POST)
    public String update(@PathVariable("id") int id, @Valid Deal deal, BindingResult result, Model model) {
        if (result.hasErrors()) {
            return "estate/deal/update";
        }

        Deal existingDeal = dealRepository.findById(id).orElse(null);

        if (existingDeal == null) {
            return "redirect:/deals";
        }

        existingDeal.setProperty(deal.getProperty());
        existingDeal.setPrice(deal.getPrice());
        existingDeal.setDate(deal.getDate());

        dealRepository.save(existingDeal);

        return "redirect:/deals";
    }

    @DeleteMapping(value = "/delete/{id}")
    public String deleteDeal(@PathVariable("id") int id, Model model) {
        dealRepository.deleteById(id);
        return "redirect:/deals";
    }
}
