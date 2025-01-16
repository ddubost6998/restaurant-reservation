package fr.hb.restaurant_reservation.controller;

import fr.hb.restaurant_reservation.model.Reservation;
import fr.hb.restaurant_reservation.service.ReservationService;
import fr.hb.restaurant_reservation.service.TableService;
import fr.hb.restaurant_reservation.service.ClientService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/reservations")
public class ReservationController {

    @Autowired
    private ReservationService reservationService;

    @Autowired
    private TableService tableService;

    @Autowired
    private ClientService clientService;

    @GetMapping
    public String listReservations(Model model) {
        model.addAttribute("reservations", reservationService.findAll());
        return "reservations/list";
    }

    @GetMapping("/add")
    public String showCreateForm(Model model) {
        model.addAttribute("reservation", new Reservation());
        model.addAttribute("tables", tableService.findAll());
        model.addAttribute("clients", clientService.findAll());
        return "reservations/form";
    }

    @PostMapping
    public String createOrUpdateReservation(
            @Valid @ModelAttribute("reservation") Reservation reservation,
            BindingResult bindingResult,
            Model model
    ) {
        if (reservation.getTableResto() != null 
            && reservation.getTableResto().getNbPlaces() != null
            && reservation.getNbPersonnes() > reservation.getTableResto().getNbPlaces()) {
            bindingResult.rejectValue("nbPersonnes", "invalid",
                    "Le nombre de personnes ne peut pas dépasser la capacité de la table.");
        }

        if (bindingResult.hasErrors()) {
            model.addAttribute("tables", tableService.findAll());
            model.addAttribute("clients", clientService.findAll());
            return "reservations/form";
        }

        try {
            reservationService.save(reservation);
        } catch (IllegalArgumentException e) {
            bindingResult.rejectValue("nbPersonnes", "invalid", e.getMessage());
            model.addAttribute("tables", tableService.findAll());
            model.addAttribute("clients", clientService.findAll());
            return "reservations/form";
        }
        return "redirect:/reservations";
    }

    @GetMapping("/edit/{id}")
    public String showEditForm(@PathVariable Long id, Model model) {
        Reservation existing = reservationService.findById(id);
        if (existing == null) {
            return "redirect:/reservations";
        }
        model.addAttribute("reservation", existing);
        model.addAttribute("tables", tableService.findAll());
        model.addAttribute("clients", clientService.findAll());
        return "reservations/form";
    }

    @GetMapping("/delete/{id}")
    public String deleteReservation(@PathVariable Long id) {
        reservationService.deleteById(id);
        return "redirect:/reservations";
    }
}
