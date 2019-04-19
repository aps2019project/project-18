package Modules;


import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;

public class GameData {
    private String opponentName;
    private String resultGame;
    private DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
    private Calendar calendar;

    public GameData(String opponentName, String resultGame) {
        this.opponentName = opponentName;
        this.resultGame = resultGame;
        calendar = Calendar.getInstance();
    }

    public void showGameData() {
        System.out.println("opponent : " + opponentName + "   match result : " + resultGame
                + " Date : " + dateFormat.format(calendar.getTime()));
    }
}
