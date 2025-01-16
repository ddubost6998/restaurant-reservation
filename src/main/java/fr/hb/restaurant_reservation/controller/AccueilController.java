package fr.hb.restaurant_reservation.controller;

import fr.hb.restaurant_reservation.service.TableService;
import fr.hb.restaurant_reservation.model.Reservation;
import fr.hb.restaurant_reservation.service.ClientService;
import fr.hb.restaurant_reservation.service.ReservationService;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class AccueilController {

    @Autowired
    private TableService tableService;

    @Autowired
    private ClientService clientService;

    @Autowired
    private ReservationService reservationService;

    @GetMapping("/")
    public String accueil(Model model) {
        model.addAttribute("totalTables", tableService.findAll().size());
        model.addAttribute("totalClients", clientService.findAll().size());
        model.addAttribute("totalReservations", reservationService.findAll().size());

        List<Reservation> allReservations = reservationService.findAll();

        model.addAttribute("reservations", allReservations);

        return "accueil";
    }
}
