package fr.hb.restaurant_reservation.model;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public class Table {

    @NotNull(message = "L'identifiant de la table ne peut pas être vide")
    private Long id;

    @NotNull(message = "Le nombre de places est obligatoire")
    @Min(value = 1, message = "Le nombre de places doit être au moins 1")
    private Integer nbPlaces;

    private Statut statut;

    public Table() {
    }

    public Table(Long id, Integer nbPlaces, Statut statut) {
        this.id = id;
        this.nbPlaces = nbPlaces;
        this.statut = statut;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getNbPlaces() {
        return nbPlaces;
    }

    public void setNbPlaces(Integer nbPlaces) {
        this.nbPlaces = nbPlaces;
    }

    public Statut getStatut() {
        return statut;
    }

    public void setStatut(Statut statut) {
        this.statut = statut;
    }

    // Définition de l'enum
    public enum Statut {
        LIBRE("Libre"),
        OCCUPE("Occupé"),
        RESERVE("Réservé"),
        INDISPONIBLE("Indisponible");

        private final String description;

        Statut(String description) {
            this.description = description;
        }

        public String getDescription() {
            return description;
        }
    }
}
