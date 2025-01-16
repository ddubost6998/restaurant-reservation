package fr.hb.restaurant_reservation.model;

import java.time.LocalDate;
import java.time.LocalTime;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public class Reservation {

    @NotBlank(message = "L'identifiant de la table ne peut pas être vide")
    private Long id;

    @NotNull(message = "La table est obligatoire")
    private Table table;

    @NotNull(message = "Le client est obligatoire")
    private Client client;

    @NotNull(message = "La date est obligatoire")
    private LocalDate dateReservation;

    @NotNull(message = "L'heure est obligatoire")
    private LocalTime heureReservation;

    @Min(value = 1, message = "Le nombre de personnes doit être au moins 1")
    private int nbPersonnes;

    public Reservation() {
    }

    public Reservation(Table tableResto, Client client, LocalDate dateReservation,
                       LocalTime heureReservation, int nbPersonnes) {
        this.table = tableResto;
        this.client = client;
        this.dateReservation = dateReservation;
        this.heureReservation = heureReservation;
        this.nbPersonnes = nbPersonnes;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Table getTableResto() {
        return table;
    }
    public void setTableResto(Table tableResto) {
        this.table = tableResto;
    }

    public Client getClient() {
        return client;
    }
    public void setClient(Client client) {
        this.client = client;
    }

    public LocalDate getDateReservation() {
        return dateReservation;
    }
    public void setDateReservation(LocalDate dateReservation) {
        this.dateReservation = dateReservation;
    }

    public LocalTime getHeureReservation() {
        return heureReservation;
    }
    public void setHeureReservation(LocalTime heureReservation) {
        this.heureReservation = heureReservation;
    }

    public int getNbPersonnes() {
        return nbPersonnes;
    }
    public void setNbPersonnes(int nbPersonnes) {
        this.nbPersonnes = nbPersonnes;
    }

}
