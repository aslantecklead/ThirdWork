package com.example.secondwork.controller;

import com.example.secondwork.model.Offer;
import com.example.secondwork.repository.OfferRepository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;

@Controller
@RequestMapping("/offers")
public class OfferController {
    private final OfferRepository offerRepository;
    @Autowired
    public OfferController(OfferRepository offerRepository) {
        this.offerRepository = offerRepository;
    }

    @GetMapping()
    public String getDeals(Model model) {
        List<Offer> offers = offerRepository.findAll();
        model.addAttribute("offers", offers);
        return "estate/offer/index";
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public String getOffer(Model model, @PathVariable("id") int id) {
        Offer offer = offerRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("This offer does not exist! ->  " + id));
        model.addAttribute("offer", offer);
        return "estate/offer/show";
    }

    @RequestMapping(value = "/create", method = RequestMethod.GET)
    public String create(Offer offer) {
        return "estate/offer/add";
    }

    @RequestMapping(value = "/store", method = RequestMethod.POST)
    public String store(@Valid Offer offer, BindingResult result, Model model) {
        if (result.hasErrors()) {
            return "estate/offer/add";
        }
        offerRepository.save(offer);
        model.addAttribute("offer", offerRepository.findAll());
        return "redirect:/offers";
    }


}
