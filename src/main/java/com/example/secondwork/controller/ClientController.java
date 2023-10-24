package com.example.secondwork.controller;

import com.example.secondwork.CrudController;
import com.example.secondwork.dao.ClientDAO;
import com.example.secondwork.model.Client;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/clients")
public class ClientController {
    private ClientDAO _clientDAO;
    CrudController _crudController;

    @Autowired
    public ClientController(ClientDAO clientDAO) {
        this._clientDAO = clientDAO;
        _crudController = new CrudController(clientDAO);
    }

    @GetMapping()
    public String index(Model model){
        model.addAttribute("clients", _clientDAO.index());
        return "estate/client/index";
    }

    @GetMapping("/{id}")
    public String show(@PathVariable("id") int id, Model model){
        model.addAttribute("client", _clientDAO.show(id));
        return "estate/client/show";
    }

    @GetMapping("/create")
    public String create(Model model) {
        model.addAttribute("title", "Add new client");
        model.addAttribute("client", new Client());
        return "estate/client/add";
    }

    @PostMapping("/store")
    public String store(Model model, @Valid @ModelAttribute("client") Client client) {
        _crudController.insert(client);
        return "redirect:/clients";
    }

    @GetMapping("/edit/{id}")
    public String edit(@PathVariable("id") int id, Model model) {
        Client client = _clientDAO.show(id);
        if (client == null) {
            return "redirect:/clients";
        }
        model.addAttribute("client", client);
        model.addAttribute("title", "Editing Client");
        return "estate/client/update";
    }

    @PostMapping("/update/{id}")
    public String update(@PathVariable("id") int id, @Valid @ModelAttribute("client") Client client) {
        client.setId(id);
        _crudController.update(client);
        return "redirect:/clients";
    }

    @PostMapping("/delete/{id}")
    String delete(Model model, @PathVariable("id") int id){
        Client client = _clientDAO.show(id);
        if(client == null){
            return "redirect:/clients";
        }
        client.setId(id);
        _crudController.delete(client);
        return "redirect:/clients";
    }
}