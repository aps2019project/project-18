package Controller;

import Modules.GameBusiness.Player.Human;
import View.Request.PlayerRequest;

public class PlayerController {
    private Human human;

    public void hadlePlayerCommands(Human haman){
        this.human = human;
        PlayerRequest request = new PlayerRequest();
        request.getNewCommand();

        while (true){
//            if (!request.isValid()){
//                continue;
//            }
        }
    }
}
