package org.example.controller;

import org.example.domain.Menu;
import org.example.service.MenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/menu")
public class MenuController {
    private final MenuService menuService;

    @Autowired
    public MenuController(MenuService menuService) {
        this.menuService = menuService;
    }

    @GetMapping("/{menuId}")
    public ResponseEntity<Menu> getMenuById(@PathVariable Integer menuId) {
        return ResponseEntity.ok(menuService.getMenuById(menuId));
    }

    @PostMapping
    public ResponseEntity<Menu> createMenu(@RequestBody @Valid Menu menu, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return ResponseEntity.badRequest().body(null);
        }
        Menu savedMenu = menuService.saveMenu(menu);
        return ResponseEntity.status(201).body(savedMenu);
    }

    @PutMapping("/{menuId}")
    public ResponseEntity<Menu> updateMenu(@PathVariable Integer menuId, @RequestBody @Valid Menu menu, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return ResponseEntity.badRequest().body(null);
        }
        Menu updatedMenu = menuService.updateMenu(menuId, menu);
        if (updatedMenu == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(updatedMenu);
    }

    @DeleteMapping("/{menuId}")
    public ResponseEntity<Void> deleteMenu(@PathVariable Integer menuId) {
        menuService.deleteMenu(menuId);
        return ResponseEntity.noContent().build();
    }

}
