package es.guesstheword;

import es.guesstheword.service.GameService;
import es.guesstheword.service.MenuService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class Runner implements CommandLineRunner {

    MenuService menuService;

    public Runner(MenuService menuService) {
        this.menuService  = menuService;
    }

    public void run(String... args) throws Exception {
        menuService.launcher();

    }
}
