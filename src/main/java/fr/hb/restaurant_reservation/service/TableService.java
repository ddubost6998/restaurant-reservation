package fr.hb.restaurant_reservation.service;

import fr.hb.restaurant_reservation.model.Table;
import fr.hb.restaurant_reservation.model.Table.Statut;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class TableService {

    private static final Map<Long, Table> tablesStorage = new HashMap<>();
    private static long counter = 1;

    /**
     * CREATE ou UPDATE
     */
    public Table save(Table table) {
        if (table.getId() == null || table.getId() == 0) {
            table.setId(counter++);
        }
        if (table.getStatut() == null) {
            table.setStatut(Statut.LIBRE);
        }
        tablesStorage.put(table.getId(), table);
        return table;
    }

    /**
     * READ ALL
     */
    public List<Table> findAll() {
        return new ArrayList<>(tablesStorage.values());
    }

    /**
     * READ ONE
     */
    public Table findById(Long id) {
        return tablesStorage.get(id);
    }

    /**
     * DELETE
     */
    public void deleteById(Long id) {
        tablesStorage.remove(id);
    }

    /**
     * Vider toutes les tables (optionnel)
     */
    public void clearAll() {
        tablesStorage.clear();
        counter = 1;
    }
}
