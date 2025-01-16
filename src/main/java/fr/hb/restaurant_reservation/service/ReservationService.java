package fr.hb.restaurant_reservation.service;

import fr.hb.restaurant_reservation.model.Reservation;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class ReservationService {

    private static final Map<Long, Reservation> reservationsStorage = new HashMap<>();
    private static long counter = 1;

    /**
     * CREATE ou UPDATE
     */
    public Reservation save(Reservation reservation) {
        if (reservation.getTableResto() != null 
            && reservation.getTableResto().getNbPlaces() != null 
            && reservation.getNbPersonnes() > reservation.getTableResto().getNbPlaces()) {
            
            throw new IllegalArgumentException(
                "Le nombre de personnes ne peut pas dépasser le nombre de places de la table."
            );
        }

        // Générer un ID s'il est null ou 0
        if (reservation.getId() == null || reservation.getId() == 0) {
            reservation.setId(counter++);
        }
        reservationsStorage.put(reservation.getId(), reservation);
        return reservation;
    }

    /**
     * READ ALL
     */
    public List<Reservation> findAll() {
        return new ArrayList<>(reservationsStorage.values());
    }

    /**
     * READ ONE
     */
    public Reservation findById(Long id) {
        return reservationsStorage.get(id);
    }

    /**
     * DELETE
     */
    public void deleteById(Long id) {
        reservationsStorage.remove(id);
    }

    /**
     * Vider toutes les réservations
     */
    public void clearAll() {
        reservationsStorage.clear();
        counter = 1;
    }
}
