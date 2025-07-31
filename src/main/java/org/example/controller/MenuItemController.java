package org.example.controller;

import org.example.domain.MenuItem;
import org.example.service.MenuItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/menu-item")
public class MenuItemController {
    private final MenuItemService menuItemService;

    @Autowired
    public MenuItemController(MenuItemService menuItemService) {
        this.menuItemService = menuItemService;
    }

    @GetMapping("/{menuItemId}")
    public ResponseEntity<MenuItem> getMenuItemById(@PathVariable Integer menuItemId) {
        return ResponseEntity.ok(menuItemService.getMenuItemById(menuItemId));
    }

    @PostMapping
    public ResponseEntity<MenuItem> createMenuItem(@RequestBody @Valid MenuItem menuItem, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return ResponseEntity.badRequest().body(null);
        }
        MenuItem savedMenuItem = menuItemService.saveMenuItem(menuItem);
        return ResponseEntity.status(201).body(savedMenuItem);
    }

    @PutMapping("/{menuItemId}")
    public ResponseEntity<MenuItem> updateMenuItem(@PathVariable Integer menuItemId, @RequestBody @Valid MenuItem menuItem, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return ResponseEntity.badRequest().body(null);
        }
        MenuItem updatedMenuItem = menuItemService.updateMenuItem(menuItemId, menuItem);
        if (updatedMenuItem == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(updatedMenuItem);
    }

    @DeleteMapping("/{menuItemId}")
    public ResponseEntity<Void> deleteMenuItem(@PathVariable Integer menuItemId) {
        menuItemService.deleteMenuItem(menuItemId);
        return ResponseEntity.noContent().build();
    }
}
