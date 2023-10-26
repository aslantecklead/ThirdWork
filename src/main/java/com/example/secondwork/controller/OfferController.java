package com.example.secondwork.controller;

import com.example.secondwork.model.Offer;
import com.example.secondwork.repository.OfferRepository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

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
        List<Offer> offers = offerRepository.findAll(Sort.by(Sort.Order.asc("id")));
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

    @RequestMapping(value = "/edit/{id}", method = RequestMethod.GET)
    public String edit(Model model, @PathVariable("id") int id) {
        Offer offer = offerRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("This offer does not exist! ->  " + id));
        model.addAttribute("offer", offer);
        return "estate/offer/update";
    }

    @RequestMapping(value = "/update/{id}", method = RequestMethod.POST)
    public String update(@PathVariable("id") int id, @Valid Offer offer, BindingResult result, Model model) {
        if (result.hasErrors()) {
            return "estate/offer/update";
        }

        Offer existingOffer = offerRepository.findById(id).orElse(null);

        if (existingOffer == null) {
            return "redirect:/offers";
        }

        existingOffer.setPropertyDescription(offer.getPropertyDescription());
        existingOffer.setPrice(offer.getPrice());
        existingOffer.setAgentName(offer.getAgentName());

        offerRepository.save(existingOffer);

        return "redirect:/offers";
    }

    @DeleteMapping(value = "/delete/{id}")
    public String deleteOffer(@PathVariable("id") int id, Model model) {
        offerRepository.deleteById(id);
        return "redirect:/offers";
    }
}
