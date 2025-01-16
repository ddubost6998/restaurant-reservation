package fr.hb.restaurant_reservation.controller;

import fr.hb.restaurant_reservation.model.Table;
import fr.hb.restaurant_reservation.service.TableService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/tables")
public class TableController {

    @Autowired
    private TableService tableService;

    @GetMapping
    public String listTables(Model model) {
        model.addAttribute("tables", tableService.findAll());
        return "tables/list";
    }

    @GetMapping("/add")
    public String showCreateForm(Model model) {
        model.addAttribute("table", new Table());
        return "tables/form";
    }

    @PostMapping
    public String createOrUpdateTable(
            @Valid @ModelAttribute("table") Table table,
            BindingResult bindingResult
    ) {
        if (bindingResult.hasErrors()) {
            return "tables/form";
        }
        tableService.save(table);
        return "redirect:/tables";
    }

    @GetMapping("/edit/{id}")
    public String showEditForm(@PathVariable Long id, Model model) {
        Table existingTable = tableService.findById(id);
        if (existingTable == null) {
            return "redirect:/tables";
        }
        model.addAttribute("table", existingTable);
        return "tables/form";
    }

    @GetMapping("/delete/{id}")
    public String deleteTable(@PathVariable Long id) {
        tableService.deleteById(id);
        return "redirect:/tables";
    }
}
