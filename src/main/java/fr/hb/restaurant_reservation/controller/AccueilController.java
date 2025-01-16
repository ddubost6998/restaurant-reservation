package fr.hb.restaurant_reservation.controller;

import fr.hb.restaurant_reservation.service.TableService;
import fr.hb.restaurant_reservation.service.ReservationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class AccueilController {

    @Autowired
    private TableService tableService;

    @Autowired
    private ReservationService reservationService;

    /**
     * Page d'accueil : affiche quelques infos globales
     */
    @GetMapping("/")
    public String accueil(Model model) {
        int nbTables = tableService.findAll().size();
        int nbReservations = reservationService.findAll().size();
        
        // On calcule le nombre de tables “libres”
        long nbTablesLibres = tableService.findAll().stream()
            .filter(t -> t.getStatut() != null && t.getStatut().name().equals("LIBRE"))
            .count();

        model.addAttribute("nbTables", nbTables);
        model.addAttribute("nbReservations", nbReservations);
        model.addAttribute("nbTablesLibres", nbTablesLibres);

        // Renvoie vers templates/accueil.html
        return "accueil";
    }
}
