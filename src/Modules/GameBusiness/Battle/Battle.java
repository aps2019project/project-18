package Modules.GameBusiness.Battle;

import Modules.Main;
import View.View.ShowAccount;

public class Battle {
    public void doOrder() {
        showMunu();
        String order;
        while (true) {
            order = Main.scanner.nextLine();
            if (order.equalsIgnoreCase("Single player")) {

            } else if (order.equalsIgnoreCase("Multi player")) {

            } else if (order.equalsIgnoreCase("back")) {
                ShowAccount.showMenu();
                return;
            } else {
                showHelpMenu();
            }
        }
    }

    private void showMunu(){
        System.out.println("1. Single player\n2. Multi player\n3. Back\n4. Help");
    }

    private void showHelpMenu(){
        System.out.println("1. Single player : play with AI");
        System.out.println("2. Multi player : play with other player");
        System.out.println("3. Back : go to previous menu");
    }
}

