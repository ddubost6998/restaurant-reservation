package fr.hb.restaurant_reservation.controller;

import fr.hb.restaurant_reservation.model.Client;
import fr.hb.restaurant_reservation.service.ClientService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/clients")
public class ClientController {

    @Autowired
    private ClientService clientService;

    @GetMapping
    public String listClients(Model model) {
        model.addAttribute("clients", clientService.findAll());
        return "clients/list";
    }

    @GetMapping("/add")
    public String showCreateForm(Model model) {
        model.addAttribute("client", new Client());
        return "clients/form";
    }

    @PostMapping
    public String createOrUpdateClient(
            @Valid @ModelAttribute Client client,
            BindingResult bindingResult
    ) {
        if (bindingResult.hasErrors()) {
            return "clients/form";
        }
        clientService.save(client);
        return "redirect:/clients";
    }

    @GetMapping("/edit/{id}")
    public String showEditForm(@PathVariable Long id, Model model) {
        Client existingClient = clientService.findById(id);
        if (existingClient == null) {
            return "redirect:/clients";
        }
        model.addAttribute("client", existingClient);
        return "clients/form";
    }

    @GetMapping("/delete/{id}")
    public String deleteClient(@PathVariable Long id) {
        clientService.deleteById(id);
        return "redirect:/clients";
    }
}
