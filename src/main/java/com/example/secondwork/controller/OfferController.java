package com.example.secondwork.controller;

import com.example.secondwork.CrudController;
import com.example.secondwork.dao.OfferDAO;
import com.example.secondwork.model.Offer;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/offers")
public class OfferController {
    private final OfferDAO offerDAO;
    CrudController _crudController;

    @Autowired
    public OfferController(OfferDAO offerDAO) {
        this.offerDAO = offerDAO;
        _crudController = new CrudController(offerDAO);
    }

    @GetMapping()
    public String index(Model model) {
        List<Offer> offers = offerDAO.index();
        model.addAttribute("offers", offers);
        return "estate/offer/index";
    }

    @GetMapping("/{id}")
    public String show(@PathVariable("id") long id, Model model) {
        Offer offer = offerDAO.show(id);
        model.addAttribute("offer", offer);
        return "estate/offer/show";
    }

    @GetMapping("/create")
    public String create(Model model) {
        model.addAttribute("title", "Add new client");
        model.addAttribute("offer", new Offer());
        return "estate/offer/add";
    }

    @PostMapping("/store")
    public String store(Model model, @Valid @ModelAttribute("offer") Offer offer) {
        _crudController.insert(offer);
        return "redirect:/offers";
    }

    @GetMapping("/edit/{id}")
    public String edit(@PathVariable("id") int id, Model model) {
        Offer offer = offerDAO.show(id);
        if (offer == null) {
            return "redirect:/offers";
        }
        model.addAttribute("offer", offer);
        model.addAttribute("title", "Editing Client");
        return "estate/offer/update";
    }

    @PostMapping("/update/{id}")
    public String update(@PathVariable("id") int id, @Valid @ModelAttribute("offer") Offer offer) {
        offer.setId(id);
        _crudController.update(offer);
        return "redirect:/offers";
    }

    @PostMapping("/delete/{id}")
    String delete(Model model, @PathVariable("id") int id){
        Offer offer = offerDAO.show(id);
        if(offer == null){
            return "redirect:/offers";
        }
        offer.setId(id);
        _crudController.delete(offer);
        return "redirect:/offers";
    }
}
