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

    public Menu getMenuById(Integer id) {
        return menuRepository.findById(id).orElse(null);
    }
    public Menu saveMenu(Menu menu) {
        return menuRepository.save(menu);
    }
    public void deleteMenu(Menu menu) {
        menuRepository.delete(menu);
    }
}
