package com.example.secondwork.controller;

import com.example.secondwork.model.Client;
import com.example.secondwork.repository.ClientRepository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

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
        List<Client> clients = clientRepository.findAll(Sort.by(Sort.Order.asc("id")));
        model.addAttribute("clients", clients);
        return "estate/client/index";
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public String getClient(Model model, @PathVariable("id") int id) {
        Client client = clientRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("This client does not exist! ->  " + id));
        model.addAttribute("client", client);
        return "estate/client/show";
    }

    @RequestMapping(value = "/create", method = RequestMethod.GET)
    public String create(Client client) {
        return "estate/client/add";
    }

    @RequestMapping(value = "/store", method = RequestMethod.POST)
    public String store(@Valid Client client, BindingResult result, Model model) {
        if (result.hasErrors()) {
            return "estate/client/add";
        }
        clientRepository.save(client);
        model.addAttribute("client", clientRepository.findAll());
        return "redirect:/clients";
    }

    @RequestMapping(value = "/edit/{id}", method = RequestMethod.GET)
    public String getCat(Model model, @PathVariable("id") int id) {
        Client client = clientRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("This client does not exist! ->  " + id));
        model.addAttribute("client", client);
        return "estate/client/update";
    }

    @RequestMapping(value = "/update/{id}", method = RequestMethod.POST)
    public String update(@PathVariable("id") int id, @Valid Client client, BindingResult result, Model model) {
        if (result.hasErrors()) {
            return "estate/client/update";
        }

        Client existingClient = clientRepository.findById(id).orElse(null);

        if (existingClient == null) {
            return "redirect:/clients";
        }

        existingClient.setName(client.getName());
        existingClient.setEmail(client.getEmail());
        existingClient.setPhonenumber(client.getPhonenumber());
        existingClient.setBudget(client.getBudget());

        clientRepository.save(existingClient);

        return "redirect:/clients";
    }

    @DeleteMapping(value = "/delete/{id}")
    public String deleteClient(@PathVariable("id") int id, Model model) {
        clientRepository.deleteById(id);
        return "redirect:/clients";
    }
}