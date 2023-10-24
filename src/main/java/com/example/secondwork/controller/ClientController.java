package com.example.secondwork.controller;

import com.example.secondwork.model.Client;
import com.example.secondwork.repository.ClientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
@RequestMapping(value = "/clients")
public class ClientController {
    private final ClientRepository clientRepository;
    @Autowired
    public ClientController(ClientRepository clientRepository) {
        this.clientRepository = clientRepository;
    }

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String getCars(@RequestParam(name = "name", defaultValue = "") String name, Model model) {
        if (name.equals("")) {
            List<Client> client = clientRepository.findAll();
            model.addAttribute("client", client);
        } else {
            List<Client> client = clientRepository.findByName(name);
            model.addAttribute("client", client);
        }
        return "estate/client/index";
    }


}