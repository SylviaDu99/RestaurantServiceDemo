package org.example.service;

import org.example.domain.Menu;
import org.example.repository.MenuRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MenuService {
    private final MenuRepository menuRepository;

    @Autowired
    public MenuService(MenuRepository menuRepository) {
        this.menuRepository = menuRepository;
    }

    public Menu getMenuById(Integer menuId) {
        return menuRepository.findById(menuId).orElse(null);
    }

    public Menu updateMenu(Integer menuId, Menu menu) {
        Menu existingMenu = getMenuById(menuId);
        if (existingMenu != null) {
            existingMenu.setName(menu.getName());
            existingMenu.setCategories(menu.getCategories());
            return menuRepository.save(existingMenu);
        }
        return null;
    }

    public Menu saveMenu(Menu menu) {
        return menuRepository.save(menu);
    }

    public void deleteMenu(Integer menuId) {
        menuRepository.deleteById(menuId);
    }
}
