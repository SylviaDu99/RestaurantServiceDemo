package org.example.service;

import org.example.domain.MenuItem;
import org.example.repository.MenuItemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MenuItemService {
    private final MenuItemRepository menuItemRepository;

    @Autowired
    public MenuItemService(MenuItemRepository menuItemRepository) {
        this.menuItemRepository = menuItemRepository;
    }

    public MenuItem getMenuItemById(Integer menuItemId) {
        return menuItemRepository.findById(menuItemId).orElse(null);
    }

    public MenuItem updateMenuItem(Integer menuItemId, MenuItem menuItem) {
        MenuItem existingMenuItem = getMenuItemById(menuItemId);
        if (existingMenuItem != null) {
            existingMenuItem.setName(menuItem.getName());
            existingMenuItem.setPrice(menuItem.getPrice());
            existingMenuItem.setDescription(menuItem.getDescription());
            existingMenuItem.setCategory(menuItem.getCategory());
            return menuItemRepository.save(existingMenuItem);
        }
        return null;
    }

    public MenuItem saveMenuItem(MenuItem menuItem) {
        return menuItemRepository.save(menuItem);
    }

    public void deleteMenuItem(Integer menuItemId) {
        menuItemRepository.deleteById(menuItemId);
    }
}
