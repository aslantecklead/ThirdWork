package com.example.secondwork.controller;

import com.example.secondwork.model.Client;
import com.example.secondwork.repository.ClientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;
@Controller
@RequestMapping("/clients")
public class ClientController {
    private final ClientRepository clientRepository;

    @Autowired
    public ClientController(ClientRepository clientRepository) {
        this.clientRepository = clientRepository;
    }

    @GetMapping()
    public String getClients(Model model) {
        List<Client> clients = clientRepository.findAll();
        model.addAttribute("clients", clients);
        return "estate/client/index";
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public String getClient(Model model, @PathVariable("id") int id) {
        Client client = clientRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("This client does not exist! ->  " + id));
        model.addAttribute("client", client);
        return "estate/client/show";
    }


}
