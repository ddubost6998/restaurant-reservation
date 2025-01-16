package fr.hb.restaurant_reservation.service;

import fr.hb.restaurant_reservation.model.Table;
import fr.hb.restaurant_reservation.model.Table.Statut;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class TableService {

    private static final Map<Long, Table> tablesStorage = new HashMap<>();
    private static long counter = 1;

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

    public List<Table> findAll() {
        return new ArrayList<>(tablesStorage.values());
    }

    public Table findById(Long id) {
        return tablesStorage.get(id);
    }

    public void deleteById(Long id) {
        tablesStorage.remove(id);
    }

    public void clearAll() {
        tablesStorage.clear();
        counter = 1;
    }
}
