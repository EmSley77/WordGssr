package es.guesstheword.service;
/*
Emanuel sleyman
2024-07-02
this class is used for menus
 */

import es.guesstheword.logic.MenuLogic;
import org.springframework.stereotype.Service;

@Service
public class MenuService {

    private MenuLogic menuLogic;

    public MenuService(MenuLogic menuLogic) {
        this.menuLogic = menuLogic;
    }

    public void launcher() {
        menuLogic.loginMenu();
    }
}
